package com.netcracker.laboratory.portlets.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.ImagesRenderer;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class PortletUtils {
    private static AtomicLong counterId = new AtomicLong(0);
    private static final int COMPANY_ID = 10154;

    public enum SESSION_KEYS {
        USER_MARKET_ID,
        USER_FILTER_SETTINGS,
        USER_CATALOG,
        USER_ORDER,
        USER_ACCOUNT_QUOTE,
        USER_EXT_ORDER, USER_ADDRESS_ENTITY,
        MARKET_ID, USER_LOCATION, USER_QUOTE, USER_CSR_CATALOG, USER_CUSTOMER,
        USER_CUSTOMER_LOGIN, USER_CUSTOMER_PASSWORD, CHAT_ID, USER_SPECIAL_OFFER_ID, USER_APPOINTMENT_SLOT_ID,
        USER_ROLE, USER_NC_PORTAL_ID, USER_PHONE_NUMS, USER_LOAN, BARCODE_FLAG, USER_CURRENT_CATEGORY, USER_ROE_SESSION_ID, USER_ROE_SALES_ORDER,
        USER_CLOSEST_STORES, USER_PROCESS_ID, IS_MIGRATE,
        USER_PROCESS_ID_LOGIN_ACTION, USER_CUSTOMER_LOGIN_ACTION, USER_QUOTE_LOGIN_ACTION,
        USER_LOCATION_LOGIN_ACTION, USER_BULK_QUANTITIES,
        USER_SKIP_ACTIVATION
    }

    @Autowired
    private RestServiceWrapper service;

    @Value("${portal.customer.account.site.id}")
    private BigInteger groupId;

    private Gson gson;

    @PostConstruct
    private void init() {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(BigInteger.class, new TypeAdapter<BigInteger>() {
            @Override
            public BigInteger read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return new BigInteger(in.nextString());
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override
            public void write(JsonWriter out, BigInteger value) throws IOException {
                out.value(String.valueOf(value));
            }
        });
        this.gson = gson.create();
    }

    public Long generateId() {
        long id = 0;
        try {
            id = CounterLocalServiceUtil.increment();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void setSessionAttr(SESSION_KEYS key, Object value) {
        RequestAttributes attr = RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key.name(), value, RequestAttributes.SCOPE_GLOBAL_SESSION);
    }

    public static <T> T getSessionAttr(SESSION_KEYS key) {
        RequestAttributes attr = RequestContextHolder.currentRequestAttributes();
        //noinspection unchecked
        return (T) attr.getAttribute(key.name(), RequestAttributes.SCOPE_GLOBAL_SESSION);
    }

    public Gson getGson() {
        return gson;
    }

    public static String getRequestParameter(PortletRequest request, String urlParameterName) {
        HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        return httpRequest.getParameter(urlParameterName);
    }

    public static String getQueryStringFromRequest(PortletRequest request) {
        StringBuilder queryString = new StringBuilder("?");
        HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        String serviceParams = httpRequest.getQueryString();
        Map<String, String[]> params = httpRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (!serviceParams.contains(entry.getKey())) {
                queryString.append(entry.getKey())
                        .append("=")
                        .append(StringUtils.join(entry.getValue(), ","))
                        .append("&");
            }
        }
        return queryString.toString();
    }

    public static PortletPreferences getPreferences(PortletRequest request) throws SystemException, PortalException {
        PortletPreferences preferences = request.getPreferences();
        String portletResource = ParamUtil.getString(request, "portletResource");
        if (Validator.isNull(portletResource)) {
            portletResource = (String) request.getAttribute("PORTLET_ID");
        }
        if (Validator.isNotNull(portletResource)) {
            preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
        }
        return preferences;
    }

    public static User getPortalUser() throws PortalException, SystemException {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        long userId = serviceContext.getUserId();
        return UserLocalServiceUtil.getUser(userId);
    }

    public boolean userExists(String email) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUserByEmailAddress(COMPANY_ID, email);
        return user != null;
    }

    public static String mapToJson(Map<String, String> map) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "{}";
        }
    }

    public static Map<String, String> jsonToMap(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = new HashMap<String, String>();
            map = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
            });
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Map<String, String> convertToMapImagesConfig(List<ImagesRenderer.ImageItem> imagesConfig) {
        Map<String, String> images = new HashMap<>();
        for (ImagesRenderer.ImageItem item : imagesConfig) {
            images.put(item.getItemId(), item.getImageUrl());
        }
        return images;
    }

    public User createUser(String firstName, String lastName,
                           String emailAddress, String jobTitle, long[] roleIds, BigInteger netcrackerId, PortletRequest request) throws SystemException, PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long creatorUserId = 0;
        Locale locale = Locale.US;
        int birthdayMonth = Calendar.JANUARY;
        int birthdayDay = 1;
        int birthdayYear = 1970;
        long[] groupIds = new long[]{groupId.longValue()};
        long[] userGroupIds = null;
        boolean sendEmail = false;

        User user = UserLocalServiceUtil.addUser(creatorUserId,
                companyId, true, null,
                StringPool.BLANK, true,
                StringPool.BLANK, emailAddress,
                0, StringPool.BLANK, locale,
                firstName, StringPool.BLANK,
                lastName, 0, 0, true,
                birthdayMonth, birthdayDay, birthdayYear,
                jobTitle, groupIds, null,
                roleIds, userGroupIds, sendEmail,
                new ServiceContext());
        user.getExpandoBridge().setAttribute("netcrackerId", netcrackerId.toString(), false);
        return user;
    }

}
