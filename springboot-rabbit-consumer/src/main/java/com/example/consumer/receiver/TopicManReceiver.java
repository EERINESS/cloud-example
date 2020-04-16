package com.example.consumer.receiver;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zzq on 2020/4/16.
 */
@Component
@RabbitListener(queues = "topic.man")
@Log
public class TopicManReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        log.info(String.format("TopicManReceiver 消费者收到的消息： %s" + testMessage.toString()));
    }
}
