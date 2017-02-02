package com.netcracker.laboratory.portlets.utils.preferences;

import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PreferenceModel {
    private String key;
    private Object value;
    private String view;
    private boolean multiple;
    private PortletPreference portletPreference;
    private Map<String, List<ListValue>> values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public PortletPreference getPortletPreference() {
        return portletPreference;
    }

    public void setPortletPreference(PortletPreference portletPreference) {
        this.portletPreference = portletPreference;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public List getMultipleValues() {
        if (isMultiple() && value instanceof List) {
            return (List) value;
        } else {
            return Collections.singletonList(value);
        }
    }

    public Map<String, List<ListValue>> getValues() {
        return values;
    }

    public void setValues(Map<String, List<ListValue>> values) {
        this.values = values;
    }
}
