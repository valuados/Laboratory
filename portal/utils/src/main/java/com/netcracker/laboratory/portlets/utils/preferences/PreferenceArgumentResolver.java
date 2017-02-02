package com.netcracker.laboratory.portlets.utils.preferences;

import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PreferenceParam;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import java.util.List;

@Component("preferenceArgumentResolver")
public class PreferenceArgumentResolver implements WebArgumentResolver {

    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService;

    @Autowired
    private PreferenceUtils preferenceUtils;

    @Override
    public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
        if (param.hasParameterAnnotation(PreferenceParam.class)) {
            PreferenceParam preferenceParam = param.getParameterAnnotation(PreferenceParam.class);
            String key = preferenceParam.value();
            if (StringUtils.isBlank(key)) {
                key = param.getParameterName();
            }

            PortletRequest portletRequest = (PortletRequest) request.getNativeRequest();
            PortletPreferences preferences = PortletUtils.getPreferences(portletRequest);
            Object paramValue = null;
            Class clazz = param.getContainingClass();
            List<PortletPreference> preferenceSetupList = PreferenceUtils.getPreferencesSetup(clazz);
            for (PortletPreference portletPreference : preferenceSetupList) {
                if (key.equals(portletPreference.value())) {
                    paramValue = resolveValue(portletPreference, preferences);
                    break;
                }
            }

            Class<?> paramType = param.getParameterType();
            if (paramValue != null && paramType.isAssignableFrom(paramValue.getClass())) {
                return paramValue;
            } else if (paramValue != null && conversionService.canConvert(paramValue.getClass(), paramType)) {
                return conversionService.convert(paramValue, paramType);
            } else {
                return UNRESOLVED;
            }
        }

        return UNRESOLVED;
    }

    private Object resolveValue(PortletPreference preferenceSettings, PortletPreferences preferencesStorage)
            throws IllegalAccessException, InstantiationException {
        PreferenceRenderer clazz = preferenceUtils.getPreferenceRenderer(preferenceSettings);
        if (preferenceSettings.multiple()) {
            return clazz.readPreferences(preferenceSettings.value(), preferenceSettings.defaultValue(), preferencesStorage);
        } else {
            return clazz.readPreference(preferenceSettings.value(), preferenceSettings.defaultValue()[0], preferencesStorage);
        }
    }
}
