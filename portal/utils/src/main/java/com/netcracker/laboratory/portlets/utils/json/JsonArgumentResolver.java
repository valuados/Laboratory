package com.netcracker.laboratory.portlets.utils.json;

import com.netcracker.laboratory.portlets.utils.PortletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.portlet.PortletRequest;

/**
 *
 */
@Component("jsonArgumentResolver")
public class JsonArgumentResolver implements WebArgumentResolver {

    @Autowired
    private PortletUtils utils;

    @Override
    public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
        if (param.hasParameterAnnotation(JSON.class)) {
            PortletRequest portletRequest = (PortletRequest) request.getNativeRequest();
            Class paramType = param.getParameterType();
            String value = portletRequest.getParameter(param.getParameterName());
            return utils.getGson().fromJson(value, paramType);
        }

        return UNRESOLVED;
    }
}
