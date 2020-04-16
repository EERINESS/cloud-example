package com.example.consumer.config;

import com.example.consumer.receiver.DirectReceiver;
import com.example.consumer.receiver.FanoutReceiverA;
import com.rabbit.provider.config.DirectRabbitConfig;
import com.rabbit.provider.config.FanoutRabbitConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by zzq on 2020/4/16.
 */
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private DirectReceiver directReceiver;  //Direct消息接收处理类
    @Autowired
    FanoutReceiverA fanoutReceiverA;//Fanout消息接收处理类A
    @Autowired
    DirectRabbitConfig directRabbitConfig;
    @Autowired
    FanoutRabbitConfig fanoutRabbitConfig;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setQueues(directRabbitConfig.TestDirectQueue());  //这里如果在这边设置了监听的队列 和下面设置了消费的监听类，那么对应的监听类可以去掉监听类的注解 ，谢谢有朋友指出这一点
        container.setMessageListener(directReceiver);
        container.addQueues(fanoutRabbitConfig.queueA());
        container.setMessageListener((MessageListener) fanoutReceiverA);
        return container;
    }
}
