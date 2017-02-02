package com.netcracker.laboratory.portlets.breadcrumbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.netcracker.entities.Student;
import com.netcracker.laboratory.portlets.utils.PortletParentController;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.AttachPreferences;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PreferenceParam;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.custom.BreadcrumbsRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListRenderer;
import com.netcracker.laboratory.services.RestServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fgaponenko on 24.11.16.
 */

@PortletPreference.List({
        @PortletPreference(value = "breadcrumbPref", renderer = BreadcrumbsRenderer.class, multiple = true)
})


@Controller
public class BreadcrumbsController extends PortletParentController {

    @Autowired
    private RestServiceWrapper service;

    private List<BreadcrumbsRenderer.BreadCrumbItem> breadcrumbList;

    @RenderMapping
    @RequestMapping("VIEW")
    private String render(Model model, RenderRequest request)
            throws SystemException, PortalException {

        breadcrumbList = new ArrayList<>();

        fillModel(model, request);

        return "breadcrumbs/view";
    }

    private void fillModel(Model model, PortletRequest request ) {

        if (breadcrumbList != null) {

            Gson gson = new Gson();
            String[] arrayOfPreferences = request.getPreferences().getValues("breadcrumbPref",null);

            for (int i = 0; i < arrayOfPreferences.length; i++) {
                breadcrumbList.add(gson.fromJson(arrayOfPreferences[i],BreadcrumbsRenderer.BreadCrumbItem.class));
            }

            model.addAttribute("breadcrumbList", breadcrumbList);
        }

    }


    @ResourceMapping("change.breadcrumbs")
    public String changeBreadcrumbs(Model model, @RequestParam Integer index) {


        changeList(index);

        model.addAttribute("breadcrumbList", breadcrumbList);

        return "breadcrumbs/list";
    }

    private void changeList(Integer index) {

        while (index+1 < breadcrumbList.size()){

            breadcrumbList.remove(index+1);

        }
    }


}
