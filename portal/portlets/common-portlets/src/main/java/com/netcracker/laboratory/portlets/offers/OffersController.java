package com.netcracker.laboratory.portlets.offers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.Category;
import com.netcracker.entities.SalesOrder;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PreferenceParam;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.CategoryListProvider;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListRenderer;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.util.Collection;

@PortletPreference.List({
        @PortletPreference(value = "category", renderer = ListRenderer.class,
                listValuesProviders = CategoryListProvider.class)

})
@Controller
public class OffersController extends PortletParentController {

    @Autowired
    private RestServiceWrapper service;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render(Model model, @PreferenceParam Integer category)
            throws SystemException, PortalException {

        if (category == null) {
            category = 1;
        }

        fillModel(model, category);
        return "offers/view";
    }

    @ResourceMapping("refresh.offers")
    public String refreshContent(Model model, @PreferenceParam Integer category) {
        fillModel(model, category);
        return "offers/view";
    }

    private void fillModel(Model model, Integer categoryId) {
        Collection offers = service.getAllOffersByCategoryId(categoryId);
        Category category = service.getCategoryById(categoryId);
        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);
        if (CollectionUtils.isNotEmpty(offers)) {
            model.addAttribute("offers", offers);
            model.addAttribute("category", category);
            model.addAttribute("salesOrder", salesOrder);
        }
    }
}
