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
@RabbitListener(queues = "topic.woman")
@Log
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage){
        log.info(String.format("TopicTotalReceiver消费者收到的消息 ： %s" + testMessage.toString()));
    }
}
