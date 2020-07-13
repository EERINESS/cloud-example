package com.rabbit.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zzq on 2020/4/16.
 *
 * Topic Exchange
 * 主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
 */
@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    //@Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.man);
    }

    //@Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.woman);
    }

    //@Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     * @return
     */
    //@Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    /**
     * 将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    //@Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
