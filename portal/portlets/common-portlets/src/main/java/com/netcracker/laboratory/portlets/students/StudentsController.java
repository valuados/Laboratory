package com.netcracker.laboratory.portlets.students;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.Offer;
import com.netcracker.entities.SalesOrder;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PreferenceParam;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListRenderer;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.math.BigInteger;
import java.util.Collection;

@PortletPreference.List({
        @PortletPreference(value = "paging", renderer = ListRenderer.class,
                listValues = {
                        @PortletPreference.ListValue(name = "Yes", value = "1"),
                        @PortletPreference.ListValue(name = "No", value = "0")
                }
        )
})
@Controller
public class StudentsController extends PortletParentController {

    @Autowired
    private RestServiceWrapper service;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render(Model model, @PreferenceParam BigInteger paging)
            throws SystemException, PortalException {
        try {
            //fillModel(model, paging);

            /*Offer firstOffer = new Offer();
            firstOffer.setCategoryId(1);
            firstOffer.setId(1);
            firstOffer.setName("Internet 100");

            SalesOrder salesOrder = service.createSalesOrder(firstOffer);

            Offer secondOffer = new Offer();
            secondOffer.setCategoryId(1);
            secondOffer.setId(2);
            secondOffer.setName("Internet 50");

            salesOrder = service.addOrderToSalesOrder(salesOrder, secondOffer);

            salesOrder = service.saveSalesOrder(salesOrder);*/


        } catch (Exception ex) {
            return "students/view";
        }
        return "students/view";
    }

    @ResourceMapping("remove.student")
    public void deleteStudent(@RequestParam Integer studentId) {
        if (studentId != null) {
            service.deleteStudent(studentId);
        }
    }

    @ResourceMapping("refresh.student")
    public String refreshTable(Model model, @PreferenceParam BigInteger paging) {
        fillModel(model, paging);
        return "students/table";
    }

    private void fillModel(Model model, BigInteger paging) {
        Collection students = service.getAllStudents();
        if (CollectionUtils.isNotEmpty(students)) {
            model.addAttribute("students", students);
            model.addAttribute("paging", paging);
        }
    }


}
