package com.netcracker.laboratory.converters;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

@Component("jsonDobMessageConverter")
public class JsonDobMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public JsonDobMessageConverter() {
        super();
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setDateFormat(sdf);
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return true;
    }
}
