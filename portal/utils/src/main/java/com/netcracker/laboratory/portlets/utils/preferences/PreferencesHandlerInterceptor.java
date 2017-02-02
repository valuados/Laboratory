package com.netcracker.laboratory.portlets.utils.preferences;

import com.netcracker.laboratory.portlets.utils.PortletUtils;
import com.netcracker.laboratory.portlets.utils.json.ExposeJSON;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.AttachPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.lang.reflect.Method;


@Component("preferenceHandlerInterceptor")
public class PreferencesHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PreferenceUtils utils;

    @Autowired
    private PortletUtils portletUtils;

    @Override
    public void postHandleRender(RenderRequest request, RenderResponse response, Object portletController, ModelAndView modelAndView) throws Exception {
        Class<?> clazz = portletController.getClass();
        if (clazz.isAnnotationPresent(AttachPreferences.class)) {
            modelAndView.addObject("portletPreferencesJson", portletUtils.getGson().toJson(utils.getPreferencesAsMap(request, clazz)));
            modelAndView.addObject("attachPreferences", true);
        } else {
            modelAndView.addObject("attachPreferences", false);
        }
        //check for data method
        Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(clazz);
        for (Method method : methods) {
            if (method.isAnnotationPresent(ExposeJSON.class)) {
                modelAndView.addObject("ExposeJSON",
                        portletUtils.getGson().toJson(method.invoke(portletController, request)));
                modelAndView.addObject("attachJSON", true);
            }
        }
    }

}