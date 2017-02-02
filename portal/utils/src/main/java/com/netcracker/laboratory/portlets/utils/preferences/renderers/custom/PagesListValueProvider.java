package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.google.common.collect.Lists;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValuesProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PagesListValueProvider implements ListValuesProvider {

    protected static final Log log = LogFactory.getLog(PagesListValueProvider.class);

    @Override
    public String getKey() {
        return "pages";
    }

    @Override
    public List<ListValue> getListValues(PortletPreference preferenceSettings) {
        List<ListValue> result = Lists.newArrayList();
        try {
            List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (Layout layout : layouts) {
                result.add(new ListValue(layout.getNameCurrentValue(), String.valueOf(layout.getPlid())));
            }
        } catch (SystemException e) {
            log.error(e.getStackTrace());
        }

        return result;
    }


}
