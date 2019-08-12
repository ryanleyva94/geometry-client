package com.leyva.geometry.client.amqp;

import org.springframework.amqp.core.Message;

public interface IMessageConsumer {

   void readMessage(Message message);

}
