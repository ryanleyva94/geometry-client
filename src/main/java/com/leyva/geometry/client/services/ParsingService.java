package com.leyva.geometry.client.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyva.geometry.client.model.QueueShape;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

public class ParsingService {

   private final ObjectMapper objectMapper;
   private final CalculatorService calculatorService;

    public ParsingService(ObjectMapper objectMapper, CalculatorService calculatorService) {
        this.objectMapper = objectMapper;
        this.calculatorService = calculatorService;
    }

    public void processMessage(String message){
        try{
            QueueShape queueShape = objectMapper.readValue(message, QueueShape.class);
            calculatorService.processShape(queueShape);
        }catch (Exception e){
            e.printStackTrace();
            throw new AmqpRejectAndDontRequeueException("The message could not be processed and will be deadlettered.");
        }
    }

}
