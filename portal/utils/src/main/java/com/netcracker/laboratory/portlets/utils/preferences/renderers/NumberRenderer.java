package com.netcracker.laboratory.portlets.utils.preferences.renderers;

import com.netcracker.laboratory.portlets.utils.preferences.PreferenceRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import java.util.List;

/**
 * Created by anpa0714 on 11/13/2015.
 */

@View("number")
@Component
public class NumberRenderer implements PreferenceRenderer<String> {
    @Override
    public String readPreference(String key, String defaultValue, PortletPreferences portletPreferences) {
        return portletPreferences.getValue(key, defaultValue);
    }

    @Override
    public List<String> readPreferences(String key, String[] defaultValue, PortletPreferences portletPreferences) {
        throw new UnsupportedOperationException(
                "Number Renderer supports only single preferences, for multiple preferences use List Renderer");
    }

    @Override
    public void savePreference(String key, String value, PortletPreferences portletPreferences) throws ReadOnlyException {
        portletPreferences.setValue(key, value);

    }

    @Override
    public void savePreferences(String key, String[] values, PortletPreferences portletPreferences) throws ReadOnlyException {
        throw new UnsupportedOperationException(
                "Number Renderer supports only single preferences, for multiple preferences use List Renderer");
    }
}
