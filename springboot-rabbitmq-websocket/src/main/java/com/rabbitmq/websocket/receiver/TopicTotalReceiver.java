package com.rabbitmq.websocket.receiver;


import com.alibaba.fastjson.JSON;
import com.rabbitmq.websocket.controller.MyWebsocket;
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
@RabbitListener(queues = "topic.data")
public class TopicTotalReceiver {

    @Autowired
    private MyWebsocket myWebsocket;

    @RabbitHandler
    public void process (Map testMessage){
        log.info(String.format("TopicTotalReceiver消费者收到消息： %s", JSON.toJSONString(testMessage)));
        myWebsocket.sendMessage(JSON.toJSONString(testMessage));
    }
}
