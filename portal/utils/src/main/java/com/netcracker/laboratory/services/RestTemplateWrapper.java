package com.netcracker.laboratory.services;

import com.netcracker.entities.Offer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

public class RestTemplateWrapper extends RestTemplate {

    @Value("${be.rest.api.url}")
    private String apiUrl;

//    public RestTemplateWrapper(ClientHttpRequestFactory requestFactory) {
//        super(requestFactory);
//    }

    @Override
    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... urlVariables) throws RestClientException {
        return super.execute(apiUrl + url, method, requestCallback, responseExtractor, urlVariables);
    }

    @Override
    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> urlVariables) throws RestClientException {
        return super.execute(apiUrl + url, method, requestCallback, responseExtractor, urlVariables);
    }

    public <T> T getForObjectQuery(String url, Class<T> responseType, Map<String, ?> urlVariables, Set<String> queryParams) throws RestClientException {
        return getForObject(url + "?" + format(queryParams), responseType, urlVariables);
    }

    public static String format(Set<String> queryParams) {
        StringBuilder result = new StringBuilder();

        for (String parameter : queryParams) {
            if (result.length() > 0) {
                result.append("&");
            }

            result.append(parameter);
            result.append("=");
            result.append("{").append(parameter).append("}");
        }

        return result.toString();
    }


}
