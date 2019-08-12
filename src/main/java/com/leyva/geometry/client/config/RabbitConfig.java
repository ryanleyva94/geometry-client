package com.leyva.geometry.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "rabbitmq.shapes")
    public RabbitProperties shapesRabbitProperties(){
        return new RabbitProperties();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));

        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory shapesRabbitListenerContainerFactory(@Qualifier("shapesRabbitProperties") RabbitProperties rabbitProperties, ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory(@Qualifier("shapesRabbitProperties") RabbitProperties rabbitProperties){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        if(rabbitProperties.getAddresses() != null){
            factory.setAddresses(rabbitProperties.getAddresses());
        }
        else if(rabbitProperties.getHost() != null){
            factory.setHost(rabbitProperties.getHost());
            factory.setPort(rabbitProperties.getPort());
        }
        if(rabbitProperties.getUsername() != null){
            factory.setUsername(rabbitProperties.getUsername());
        }
        if(rabbitProperties.getPassword() != null){
            factory.setPassword(rabbitProperties.getPassword());
        }
        if(rabbitProperties.getVirtualHost() != null){
            factory.setVirtualHost(rabbitProperties.getVirtualHost());
        }
        factory.setRequestedHeartBeat(60);
        return factory;
    }

}
