package com.rabbitmq.websocket.receiver;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.websocket.websocket.RabbitMqWebsocket;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zzq on 2020/4/20.
 */
@Log
@Component
public class TopicTotalReceiver {

    @Autowired
    private RabbitMqWebsocket rabbitMqWebsocket;

    @RabbitListener(queues = "topic.data")
    @RabbitHandler
    public void process (Map testMessage){
        log.info(String.format("TopicTotalReceiver消费者收到消息： %s", JSON.toJSONString(testMessage)));
        rabbitMqWebsocket.sendMessage(JSON.toJSONString(testMessage));
    }

    @RabbitListener(queues = "topic.da")
    @RabbitHandler
    public void process1 (Map testMessage){
        log.info(String.format("TopicDaReceiver消费者收到消息： %s", JSON.toJSONString(testMessage)));
        rabbitMqWebsocket.sendMessage(JSON.toJSONString(testMessage));
    }
}
