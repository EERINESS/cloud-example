package com.rabbitmq.websocket.receiver;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.websocket.entity.WebReturn;
import com.rabbitmq.websocket.websocket.RabbitMqWebsocket;
import com.rabbitmq.websocket.websocket.StudentWebsocket;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;

/**
 * Created by zzq on 2020/4/20.
 */
@Log
@Component
public class TopicTotalReceiver {

    @Autowired
    private RabbitMqWebsocket rabbitMqWebsocket;
    @Autowired
    private StudentWebsocket studentWebsocket;

    @RabbitListener(queues = "topic.data")
    @RabbitHandler
    public void process (Map testMessage){
        log.info(String.format("TopicTotalReceiver消费者收到消息： %s", JSON.toJSONString(testMessage)));
        rabbitMqWebsocket.sendMessage(JSON.toJSONString(testMessage));
    }

    @RabbitListener(queues = "topic.da")
    @RabbitHandler
    public void process1 (String testMessage){
        log.info(String.format("TopicDaReceiver消费者收到消息： %s", JSON.toJSONString(testMessage)));
//        Session session = (Session) testMessage.get("session");
//        WebReturn webReturn = (WebReturn) testMessage.get("result");
//        //rabbitMqWebsocket.sendMessage(JSON.toJSONString(testMessage));
//        studentWebsocket.onMessage(session, JSON.toJSONString(webReturn));
    }
}
