package com.netcracker.laboratory.portlets.utils.preferences;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import java.util.List;
import java.util.Map;

@Component
public class PreferenceUtils implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static List<PortletPreference> getPreferencesSetup(Class clazz) {
        List<PortletPreference> result = Lists.newArrayList();
        if (clazz.isAnnotationPresent(PortletPreference.class)) {
            result.add((PortletPreference) clazz.getAnnotation(PortletPreference.class));
        } else if (clazz.isAnnotationPresent(PortletPreference.List.class)) {
            PortletPreference[] portletPreferences = ((PortletPreference.List) clazz.getAnnotation(PortletPreference.List.class)).value();
            result.addAll(Lists.newArrayList(portletPreferences));
        }
        return result;
    }

    public PreferenceRenderer getPreferenceRenderer(PortletPreference preference) {
        return applicationContext.getBean(preference.renderer());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Map<String, Object> getPreferencesAsMap(PortletRequest request, Class controller) throws PortalException, SystemException {
        List<PortletPreference> setup = getPreferencesSetup(controller);
        Map<String, Object> result = Maps.newHashMap();
        PortletPreferences preferences = PortletUtils.getPreferences(request);
        for (PortletPreference portletPreference : setup) {
            PreferenceRenderer renderer = getPreferenceRenderer(portletPreference);
            if (portletPreference.multiple()) {
                result.put(portletPreference.value(), renderer.readPreferences(portletPreference.value(), portletPreference.defaultValue(), preferences));
            } else {
                result.put(portletPreference.value(), renderer.readPreference(portletPreference.value(), portletPreference.defaultValue()[0], preferences));

            }
        }
        return result;
    }
}
