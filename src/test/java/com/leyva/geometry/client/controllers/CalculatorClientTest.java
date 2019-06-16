package com.leyva.geometry.client.controllers;


import com.leyva.geometry.client.contollers.CalculatorClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class CalculatorClientTest {

    private MockRestServiceServer mockRestServiceServer;

    private RestTemplate restTemplate;

    private HttpEntity<?> httpEntity;

    @Autowired
    private CalculatorClient calculatorClient;

    @Before
    public void setup(){
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<>(headers);
        calculatorClient = new CalculatorClient(restTemplate, httpEntity);
        ReflectionTestUtils.setField(calculatorClient, "baseUrl", "http://localhost:8080/");
        ReflectionTestUtils.setField(calculatorClient, "urlExtension", "shape");
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testCalculateCube(){
        mockRestServiceServer.expect(requestTo("http://localhost:8080/shape/cube?width=10.0"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.APPLICATION_JSON));

        calculatorClient.calculateCube(10);
    }

}
