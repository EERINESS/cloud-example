package com.rabbit.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zzq on 2020/7/8.
 */
@Configuration
public class StudentRabbitConfig {
    @Autowired
    RabbitAdmin rabbitAdmin;

    //绑定键
    public final static String update = "student.update";

    @Bean
    public Queue firstQueue() {
        return new Queue(StudentRabbitConfig.update);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("studentExchange");
    }

    /**
     * 将firstQueue和topicExchange绑定,而且绑定的键值为student.update
     * 这样只要是消息携带的路由键是student.update,才会分发到该队列
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(update);
    }



    //创建初始化RabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //创建交换机和对列
    @Bean
    public void createExchangeQueue (){
        rabbitAdmin.declareExchange(exchange());
        rabbitAdmin.declareQueue(firstQueue());
    }

}
