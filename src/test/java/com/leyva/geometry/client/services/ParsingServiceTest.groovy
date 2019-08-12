package com.leyva.geometry.client.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.leyva.geometry.client.model.QueueShape
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import spock.lang.Specification

class ParsingServiceTest extends Specification {

    private ObjectMapper objectMapper
    private CalculatorService calculatorService

    private ParsingService parsingService

    def setup(){
        objectMapper = Mock(ObjectMapper)
        calculatorService = Mock(CalculatorService)

        parsingService = new ParsingService(objectMapper, calculatorService)
    }


    def "test processShapeCalledOn"(){
        given:
        String message = "test"
        QueueShape queueShape = new QueueShape()

        when:
        objectMapper.readValue(_, _) >> queueShape

        parsingService.processMessage(message)

        then:
        1 * calculatorService.processShape(queueShape)
    }

    def "test amqpRejectAndDontRequeueExceptionThrownUponException"(){
        given:
        String message = "test"
        QueueShape queueShape = new QueueShape()

        when:
        objectMapper.readValue(_, _) >> queueShape
        calculatorService.processShape(queueShape) >> {throw new RuntimeException()}

        parsingService.processMessage(message)

        then:
        final AmqpRejectAndDontRequeueException exception = thrown()
    }

}
