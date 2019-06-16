package com.leyva.geometry.client.contollers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CalculatorClient {

    @Value("${api.baseUrl}")
    private String baseUrl;

    @Value("${api.urlExtension}")
    private String urlExtension;

    private final RestTemplate restTemplate;
    private final HttpEntity<?> httpEntity;

    public CalculatorClient(RestTemplate restTemplate, HttpEntity<?> httpEntity) {
        this.restTemplate = restTemplate;
        this.httpEntity = httpEntity;
    }

    public void calculateCube(double width){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + urlExtension + "/cube")
                .queryParam("width", width);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, String.class);

        System.out.println(response.getBody());
    }

}
