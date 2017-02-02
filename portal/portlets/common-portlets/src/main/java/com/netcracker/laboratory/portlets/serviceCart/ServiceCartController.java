package com.netcracker.laboratory.portlets.serviceCart;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.SalesOrder;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.portlets.utils.PortletUtils;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;


@Controller
public class ServiceCartController extends PortletParentController {

    @Autowired
    private RestServiceWrapper service;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render(Model model)
            throws SystemException, PortalException {
        fillModel(model);
        return "serviceCart/view";
    }

    @ResourceMapping("refresh.orders")
    public String refreshTable(Model model) {
        fillModel(model);
        return "serviceCart/table";
    }

    @ResourceMapping("add.new.order")
    public String addOrder(Model model, @RequestParam Integer id) {
        addOrder(id);
        fillModel(model);
        return "serviceCart/table";
    }

    @ResourceMapping("remove.order")
    public String removeOrder(Model model, @RequestParam Integer id) {
        deleteOrder(id);
        fillModel(model);
        return "serviceCart/table";
    }

    private void addOrder(Integer offerId) {
        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);

        if (salesOrder==null){
            salesOrder = service.createSalesOrder(offerId);
        }
        else{
            salesOrder = service.addOrderToSalesOrder(salesOrder, offerId);
        }

        PortletUtils.setSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE, salesOrder);
    }

    private void deleteOrder(Integer orderId) {
        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);

        if (salesOrder != null) {
            salesOrder = service.deleteOrderFromSalesOrder(salesOrder, orderId);
        }

        if (salesOrder.getOrders().isEmpty()) {
            PortletUtils.setSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE, null);
        } else {
            PortletUtils.setSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE, salesOrder);
        }
    }

    private void fillModel(Model model) {
        SalesOrder salesOrder = PortletUtils.getSessionAttr(PortletUtils.SESSION_KEYS.USER_QUOTE);
        model.addAttribute("salesOrder", salesOrder);
    }

}