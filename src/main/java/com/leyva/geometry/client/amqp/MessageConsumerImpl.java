package com.leyva.geometry.client.amqp;

import com.leyva.geometry.client.services.ParsingService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageConsumerImpl implements IMessageConsumer {

    private final ParsingService parsingService;

    public MessageConsumerImpl(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.shapes.main}", containerFactory = "shapesRabbitListenerContainerFactory")
    public void readMessage(Message message) {
        String messageString = new String(message.getBody());

        parsingService.processMessage(messageString);
    }
}
