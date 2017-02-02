package com.netcracker.laboratory.portlets.utils.preferences.renderers.list;

import com.google.common.collect.ImmutableList;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("defaultListValueProvider")
public class StaticListValuesProvider implements ListValuesProvider {
    @Override
    public String getKey() {
        return "defaultValues";
    }

    @Override
    public List<ListValue> getListValues(PortletPreference preferenceSettings) {
        ImmutableList.Builder<ListValue> listValuesBuilder = ImmutableList.builder();
        PortletPreference.ListValue[] listValuesArray = preferenceSettings.listValues();
        if (listValuesArray != null && listValuesArray.length > 0) {
            for (PortletPreference.ListValue listValue : listValuesArray) {
                listValuesBuilder.add(new ListValue(listValue.name(), listValue.value()));
            }
        }
        return listValuesBuilder.build();
    }
}
