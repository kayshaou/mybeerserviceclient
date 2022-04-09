package com.anocha.mybeerclient.web.client;

import com.anocha.mybeerclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@ConfigurationProperties(prefix="anocha.pkg", ignoreUnknownFields = false)
@Component
public class BeerClient {
    public final String BEER_ENDPOINT_V1 = "/api/v1/beer/";
    private String apihost;

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public String getApihost() {
        return this.apihost;
    }

    private final RestTemplate restTemplate;

    public BeerClient(RestTemplateBuilder restTemplatebuilder) {
        this.restTemplate = restTemplatebuilder.build();
    }

    public BeerDto getBeerById(UUID beerId) {
        return restTemplate.getForObject(apihost + BEER_ENDPOINT_V1 + beerId, BeerDto.class);
    }

    public ResponseEntity saveBeer(BeerDto beerDto) {
        return restTemplate.postForEntity(apihost + BEER_ENDPOINT_V1, beerDto, BeerDto.class);
    }

}
