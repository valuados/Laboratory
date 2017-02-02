package com.netcracker.laboratory.portlets.utils.json;

import com.google.common.collect.Lists;
import com.netcracker.laboratory.portlets.utils.preferences.PreferenceRenderer;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JsonAwareRenderer<T extends JsonBean> implements PreferenceRenderer<T> {

    @Autowired
    private PortletUtils portletUtils;

    @Override
    public T readPreference(String key, String defaultValue, PortletPreferences portletPreferences) {
        String value = portletPreferences.getValue(key, defaultValue);
        if (StringUtils.isBlank(value)) {
            T bean = (T) portletUtils.getGson().fromJson(value,
                    this.getClass().getAnnotation(JSONAwareClass.class).value());
            if (bean != null) {
                bean.setJson(value);
            }
            return bean;
        }
        return null;
    }

    @Override
    public List<T> readPreferences(String key, String[] defaultValue, PortletPreferences portletPreferences) {
        List<T> result = Lists.newArrayList();
        String[] values = portletPreferences.getValues(key, defaultValue);
        if (values != null) {
            for (String value : values) {
                Map<String, String> map = portletUtils.jsonToMap(value);
                if (map == null) {
                    value = "{}";
                    break;
                }

                for (String mapKey : map.keySet()) {
                    String newValue = StringEscapeUtils.escapeHtml4(map.get(mapKey));
                    map.put(mapKey, newValue);
                }

                value = portletUtils.mapToJson(map);
                T bean = (T) portletUtils.getGson().fromJson(value,
                        this.getClass().getAnnotation(JSONAwareClass.class).value());
                if (bean != null) {
                    bean.setJson(value);
                    result.add(bean);
                }
            }
        }

        return result;
    }

    @Override
    public void savePreference(String key, String value, PortletPreferences portletPreferences) throws ReadOnlyException {
        portletPreferences.setValue(key, value);
    }

    @Override
    public void savePreferences(String key, String[] values, PortletPreferences portletPreferences) throws ReadOnlyException {
        portletPreferences.setValues(key, values);
    }
}
