package com.leyva.geometry.client;

import com.leyva.geometry.client.contollers.CalculatorClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final CalculatorClient calculatorClient;

    public ApplicationStartup(CalculatorClient calculatorClient) {
        this.calculatorClient = calculatorClient;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        calculatorClient.calculateCube(5.0);
    }
}
