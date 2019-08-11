package com.leyva.geometry.client;

import com.leyva.geometry.client.services.CalculatorService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final CalculatorService calculatorService;

    public ApplicationStartup(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        calculatorService.processShape();
    }
}
