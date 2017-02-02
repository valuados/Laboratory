package com.netcracker.laboratory.auth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Component("authFactory")
public class AuthClientHttpRequestFactory extends
        HttpComponentsClientHttpRequestFactory {

    protected static final Log log = LogFactory.getLog(AuthClientHttpRequestFactory.class);

    @Value("${back.end.server.url}")
    private String backEnd;

//    @Value("${be.rest.api.url.port}")
//    private int port;

    public AuthClientHttpRequestFactory() {
        super();
        HttpClient client = HttpClientBuilder.create().evictIdleConnections(10L, TimeUnit.SECONDS).build();
        setHttpClient(client);
    }

    @Override
    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {

//        HttpHost host = new HttpHost(backEnd, port, null);
        HttpHost host = new HttpHost(backEnd);
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);

        HttpClientContext localcontext = HttpClientContext.create();
        localcontext.setAuthCache(authCache);

        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

        long userId = serviceContext.getUserId();
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();

            credsProvider.setCredentials(new AuthScope(host), new UsernamePasswordCredentials(user.getLogin(), user.getPassword()));

            localcontext.setCredentialsProvider(credsProvider);
        } catch (PortalException | SystemException e) {
            log.error(e.getStackTrace());
        }
        return localcontext;
    }

}