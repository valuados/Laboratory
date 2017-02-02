package com.netcracker.laboratory.portlets.utils.preferences.renderers.list;

import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;

import java.util.List;

public interface ListValuesProvider {

    String getKey();

    List<ListValue> getListValues(PortletPreference preferenceSettings);
}
