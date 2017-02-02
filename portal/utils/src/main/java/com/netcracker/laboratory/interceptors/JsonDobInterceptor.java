package com.netcracker.laboratory.interceptors;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("jsonDobInterceptor")
public class JsonDobInterceptor implements ClientHttpRequestInterceptor {

    public final static String JSON_DOB_VALUE = "application/json";

    @Value("${portal.enable.rest.logging}")
    private boolean loggingEnabled;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE + ", " + JSON_DOB_VALUE);
        headers.set("Content-Type", JSON_DOB_VALUE);
        logging(request, body);
        return execution.execute(request, body);
    }



    private void logging(HttpRequest request, byte[] body) {
        debug("<<< REQUEST >>>");
        debug("\n" + request.getURI() + " \n");
        debug("<<< BODY >>>");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        if (loggingEnabled) {
            JsonElement je = jp.parse(new String(body));
            String prettyJsonString = gson.toJson(je);
            debug("\n" + prettyJsonString + "\n");
        }
    }

    private void debug(String body) {
        System.out.println(body);
    }

}
