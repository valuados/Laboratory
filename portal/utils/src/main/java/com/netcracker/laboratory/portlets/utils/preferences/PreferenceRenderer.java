package com.netcracker.laboratory.portlets.utils.preferences;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import java.util.List;

public interface PreferenceRenderer<T> {
    T readPreference(String key, String defaultValue, PortletPreferences portletPreferences);

    List<T> readPreferences(String key, String[] defaultValue, PortletPreferences portletPreferences);

    void savePreference(String key, String value, PortletPreferences portletPreferences) throws ReadOnlyException;

    void savePreferences(String key, String[] values, PortletPreferences portletPreferences) throws ReadOnlyException;
}
