package com.netcracker.laboratory.portlets.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.stereotype.Component;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.math.BigInteger;

/**
 *
 */
@Component("customRequestResolver")
public class CustomRequestResolver implements ModelAndViewResolver {

    private SimpleModule module;

    @PostConstruct
    private void init() {
        module = new SimpleModule("BigIntegerSerializerModule",
                Version.unknownVersion());
        module.addSerializer(BigInteger.class, new ToStringSerializer());
    }

    @Override
    public ModelAndView resolveModelAndView(Method handlerMethod, Class handlerType, Object returnValue, ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
        if (returnValue instanceof String || returnValue == null) {
            return UNRESOLVED;
        } else {
            ModelAndView mav = new ModelAndView();
            MappingJackson2JsonView v = new MappingJackson2JsonView();
            v.getObjectMapper().registerModule(module);
            mav.addObject("data", returnValue);
            mav.setView(v);
            return mav;
        }
    }
}
