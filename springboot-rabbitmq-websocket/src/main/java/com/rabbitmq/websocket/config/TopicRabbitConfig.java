package com.rabbitmq.websocket.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zzq on 2020/4/20.
 */
@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String DATA_QUEUE = "topic.data";

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicRabbitConfig.DATA_QUEUE);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with("topic.#");
    }
}
