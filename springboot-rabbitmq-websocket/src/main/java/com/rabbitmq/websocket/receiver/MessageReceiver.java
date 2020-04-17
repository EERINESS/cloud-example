package com.rabbitmq.websocket.receiver;

import com.rabbitmq.websocket.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zzq on 2020/4/17.
 */
@Component
@RabbitListener(queues = "consumer_queue")
public class MessageReceiver {
    @RabbitHandler
    public void processMessage1(String message) {
        System.out.println(message);
    }

    @RabbitHandler
    public void processMessage2(byte[] message) {
        System.out.println(new String(message));
    }

    @RabbitHandler
    public void processMessage3(User user) {
        System.out.println(user.getUserName());
    }
}
