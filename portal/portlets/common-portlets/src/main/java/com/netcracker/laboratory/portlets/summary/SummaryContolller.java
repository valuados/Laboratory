package com.netcracker.laboratory.portlets.summary;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.SalesOrder;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PreferenceParam;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.PortletSession;

/**
 * Created by fgaponenko on 20.12.16.
 */

@Controller
public class SummaryContolller extends PortletParentController{


    @Autowired
    private RestServiceWrapper service;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render(Model model)
            throws SystemException, PortalException {
        fillModel(model);
        return "summary/view";
    }

    @ResourceMapping("refresh.summary")
    public String viewSummary(Model model) {
        fillModel(model);
        return "summary/view";
    }

    @ResourceMapping("save.so")
    public String saveSalesOrder() {
        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);
        service.saveSalesOrder(salesOrder);
        PortletUtils.setSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE, null);

        return "summary/view";
    }

    private void fillModel(Model model) {

        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);

        model.addAttribute("salesOrder", salesOrder);
    }

}
