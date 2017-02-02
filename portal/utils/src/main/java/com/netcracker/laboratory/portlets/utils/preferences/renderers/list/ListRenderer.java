package com.netcracker.laboratory.portlets.utils.preferences.renderers.list;

import com.google.common.collect.Lists;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import java.util.Arrays;
import java.util.List;

@View("list")
@Component
public class ListRenderer implements PreferenceRenderer<String> {
    @Override
    public String readPreference(String key, String defaultValue, PortletPreferences portletPreferences) {
        return portletPreferences.getValue(key, defaultValue);
    }

    @Override
    public List<String> readPreferences(String key, String[] defaultValue, PortletPreferences portletPreferences) {
        String[] values = portletPreferences.getValues(key, defaultValue);
        if(values != null){
            return Arrays.asList(values);
        } else return Lists.newArrayList();
    }

    @Override
    public void savePreference(String key, String value, PortletPreferences portletPreferences)
            throws ReadOnlyException {
        portletPreferences.setValue(key, value);
    }

    @Override
    public void savePreferences(String key, String[] values, PortletPreferences portletPreferences)
            throws ReadOnlyException {
        portletPreferences.setValues(key, values);
    }
}
