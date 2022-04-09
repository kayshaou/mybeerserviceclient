package com.anocha.mybeerclient.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jt on 2019-08-08.
 */
@Component
@Slf4j
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    private final Integer maxtotal;
    private final Integer defaultmaxperroute;
    private final Integer connectionrequesttimeout;
    private final Integer sockettimeout;

    public BlockingRestTemplateCustomizer(@Value("${cfg.maxtotal}") Integer maxtotal,
                                          @Value("${cfg.defaultmaxperroute}") Integer defaultmaxperroute,
                                          @Value("${cfg.connectionrequesttimeout}") Integer connectionrequesttimeout,
                                          @Value("${cfg.sockettimeout}") Integer sockettimeout){

        log.info("maxtotal {} {} {} {}",maxtotal, defaultmaxperroute,connectionrequesttimeout, sockettimeout);

        this.maxtotal = maxtotal;
        this.defaultmaxperroute = defaultmaxperroute;
        this.connectionrequesttimeout = connectionrequesttimeout;
        this.sockettimeout = sockettimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxtotal);
        connectionManager.setDefaultMaxPerRoute(defaultmaxperroute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionrequesttimeout)
                .setSocketTimeout(sockettimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}