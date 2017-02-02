package com.netcracker.laboratory.configuration;

import com.netcracker.laboratory.services.RestCartWrapper;
import com.netcracker.laboratory.services.RestServiceWrapper;
import com.netcracker.laboratory.services.RestTemplateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortalConfiguration {

    @Autowired
    RestTemplateWrapper restTemplateWrapper;

    @Bean
    public RestServiceWrapper getRestService() {
        return new RestServiceWrapper(restTemplateWrapper);
    }

    @Bean
    public RestCartWrapper getRestCart() {
        return new RestCartWrapper(restTemplateWrapper);
    }
}
