package com.leyva.geometry.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyva.geometry.client.amqp.IMessageConsumer;
import com.leyva.geometry.client.amqp.MessageConsumerImpl;
import com.leyva.geometry.client.contollers.CalculatorClient;
import com.leyva.geometry.client.model.Shape;
import com.leyva.geometry.client.repositories.ShapeRepository;
import com.leyva.geometry.client.services.CalculatorService;
import com.leyva.geometry.client.services.ParsingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${security.username}")
    private String username;

    @Value("${security.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(username, password).build();
        restTemplate.getMessageConverters().add(0, mappingJacksonHttpMessageConverter);
        return restTemplate;
    }

    @Bean
    public HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @Bean
    public HttpEntity<?> httpEntity(HttpHeaders headers){
        return new HttpEntity<>(headers);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public IMessageConsumer iMessageConsumer(ParsingService parsingService){
        return new MessageConsumerImpl(parsingService);
    }

    @Bean
    public ParsingService parsingService(ObjectMapper objectMapper, CalculatorService calculatorService){
        return new ParsingService(objectMapper, calculatorService);
    }

    @Bean
    public CalculatorService calculatorService(CalculatorClient calculatorClient, ShapeRepository shapeRepository){
        return new CalculatorService(calculatorClient, shapeRepository);
    }

    @Bean
    public CalculatorClient calculatorClient(RestTemplate restTemplate, HttpEntity<?> httpEntity){
        return new CalculatorClient(restTemplate, httpEntity);
    }

    @Bean
    public ShapeRepository shapeRepository(ObjectMapper objectMapper){
        return new ShapeRepository(objectMapper);
    }



}
