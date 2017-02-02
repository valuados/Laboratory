package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.entities.Category;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValuesProvider;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryListProvider implements ListValuesProvider {

    @Autowired
    private RestServiceWrapper service;

    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public List<ListValue> getListValues(PortletPreference preferenceSettings) {
        Collection<Category> offers = service.getAllCategories();
        return offers.stream()
                .map(category -> new ListValue(category.getName(), category.getId().toString()))
                .collect(Collectors.toList());
    }

}
