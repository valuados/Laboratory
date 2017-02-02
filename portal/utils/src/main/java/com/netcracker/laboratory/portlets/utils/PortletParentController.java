package com.netcracker.laboratory.portlets.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceModel;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceUtils;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValuesProvider;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.portlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@RequestMapping({"VIEW", "EDIT"})
@Controller
public class PortletParentController implements ApplicationContextAware {

    protected static final Log log = LogFactory.getLog(PortletParentController.class);
    private static final BigInteger DEFAULT_GROUP_ID = BigInteger.valueOf(12377L);

    @Value("#{systemProperties['printStackTraceRender']?:true}")
    private boolean printStackTraceRender;

    @Value("#{systemProperties['printStackTraceAjax']?:true}")
    private boolean printStackTraceAjax;

    @Value("${portal.customer.account.site.id}")
    private BigInteger groupId;

    @Autowired
    private PreferenceUtils preferenceUtils;

    private ApplicationContext applicationContext;

    @RenderMapping
    @RequestMapping("EDIT")
    private String renderEdit(Model model, RenderRequest request)
            throws PortalException, SystemException {
        List<PreferenceModel> models = Lists.newArrayList();
        PortletPreferences preferences = PortletUtils.getPreferences(request);
        Class clazz = this.getClass();
        List<PortletPreference> setup = PreferenceUtils.getPreferencesSetup(clazz);
        for (PortletPreference portletPreference : setup) {
            models.add(loadPreferencesModel(preferences, portletPreference));
        }
        model.addAttribute("preferences", models);
        return "/preferences/preferences";
    }

    @ResourceMapping("preferences.save")
    protected void save(ResourceRequest request)
            throws ReadOnlyException, IOException, ValidatorException, PortalException, SystemException {
        PortletPreferences preferences = PortletUtils.getPreferences(request);
        Class clazz = this.getClass();
        List<PortletPreference> setup = PreferenceUtils.getPreferencesSetup(clazz);
        for (PortletPreference portletPreference : setup) {
            savePreference(preferences, portletPreference, request);
        }
        preferences.store();
    }

    @ResourceMapping("get.images.by.folder")
    private String getImagesByFolder(Model model, ResourceRequest request, @RequestParam Long folderId)
            throws PortletException, IOException, PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<String> images = Lists.newArrayList();
        if (groupId == null) {
            groupId = DEFAULT_GROUP_ID;
            System.out.println("Group ID == null at PortletParentController\n" +
                    "Add portal-setup-wizard.properties  to your portlet resource xml to fix it");
        }
        List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(groupId.longValue(), folderId);
        for (FileEntry file : fileEntries) {
            StringBuilder url = new StringBuilder("/documents/");
            url.append(themeDisplay.getScopeGroupId())
                    .append("/")
                    .append(file.getFolderId())
                    .append("/")
                    .append(file.getTitle());
            images.add(url.toString());
        }
        model.addAttribute("images", images);
        return "/preferences/views/custom/images.selector";
    }

    private PreferenceModel loadPreferencesModel(PortletPreferences preferencesStorage, PortletPreference preferenceSettings) {
        PreferenceModel preferenceModel = new PreferenceModel();
        preferenceModel.setPortletPreference(preferenceSettings);
        String key = preferenceSettings.value();
        preferenceModel.setKey(key);
        PreferenceRenderer renderer = preferenceUtils.getPreferenceRenderer(preferenceSettings);
        preferenceModel.setMultiple(preferenceSettings.multiple());

        if (preferenceSettings.multiple()) {
            preferenceModel.setValue(renderer.readPreferences(key, preferenceSettings.defaultValue(), preferencesStorage));
        } else {
            preferenceModel.setValue(renderer.readPreference(key, preferenceSettings.defaultValue()[0], preferencesStorage));
        }

        preferenceModel.setView("/preferences/views/" + renderer.getClass().getAnnotation(View.class).value() + ".ftl");
        preferenceModel.setValues(getAvailableListValues(preferenceSettings));
        return preferenceModel;
    }

    public Map<String, List<ListValue>> getAvailableListValues(PortletPreference preferenceSettings) {
        ImmutableMap.Builder<String, List<ListValue>> builder = ImmutableMap.builder();
        for (Class<? extends ListValuesProvider> providerClass : preferenceSettings.listValuesProviders()) {
            ListValuesProvider advModelProvider = applicationContext.getBean(providerClass);
            if (advModelProvider != null) {
                builder.put(advModelProvider.getKey(), advModelProvider.getListValues(preferenceSettings));
            }
        }
        return builder.build();
    }

    private void savePreference(PortletPreferences preferences, PortletPreference portletPreference, ResourceRequest request)
            throws ReadOnlyException {
        String key = portletPreference.value();
        String[] params = request.getParameterValues(key);
        PreferenceRenderer renderer = preferenceUtils.getPreferenceRenderer(portletPreference);
        if (portletPreference.multiple()) {
            renderer.savePreferences(key, params, preferences);
        } else {
            if (params != null && params.length > 0) {
                renderer.savePreference(key, params[0], preferences);
            } else {
                renderer.savePreference(key, null, preferences);
            }
        }
    }

    @ExceptionHandler(Exception.class)
    private Object error(Exception ex, PortletRequest request, PortletResponse response) {
        return resolveGeneralError(ex, request, response);
    }

    private Object resolveGeneralError(Exception ex, PortletRequest request, PortletResponse response) {
        log.error(ex.getMessage(), ex);
        if (request instanceof ResourceRequest) {
            ModelAndView mav = new ModelAndView();
            MappingJackson2JsonView v = new MappingJackson2JsonView();
            if (printStackTraceRender && printStackTraceAjax) {
                mav.addObject("stackTrace", ExceptionUtils.getFullStackTrace(ex));
            }
            mav.addObject("message", ex.getMessage());
            response.setProperty(ResourceResponse.HTTP_STATUS_CODE, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            mav.setView(v);
            return mav;
        } else {
            request.setAttribute("message", ex);
            if (printStackTraceRender) {
                request.setAttribute("stackTrace", ExceptionUtils.getFullStackTrace(ex));
            }
            return "error";
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected <T> T tryAgain(Callable<T> callable, Class<? extends Exception> ignore) throws Exception {
        try {
            return callable.call();
        } catch (Exception e) {
            if (ignore.equals(e.getClass())) {
                log.warn("Ignore error", e);
                return callable.call();
            } else {
                throw e;
            }
        }
    }
}
