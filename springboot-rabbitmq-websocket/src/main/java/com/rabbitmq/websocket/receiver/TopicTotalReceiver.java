package com.rabbitmq.websocket.receiver;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.entity.WebsocketInfo;
import com.rabbitmq.websocket.websocket.StudentWebsocket;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;


/**
 * Created by zzq on 2020/4/20.
 */
@Log
@Component
public class TopicTotalReceiver {

    @Autowired
    private StudentWebsocket studentWebsocket;

    @RabbitListener(queues = "student.update")
    @RabbitHandler
    public void process (String strMessage) throws JsonProcessingException {
        log.info(String.format("TopicTotalReceiver消费者收到消息： %s", JSON.toJSONString(strMessage)));
        ObjectMapper objectMapper = new ObjectMapper();
        WebsocketInfo websocketInfo = objectMapper.readValue(strMessage, WebsocketInfo.class);
        Session session = studentWebsocket.sessionMap.get(websocketInfo.getSessionId());
        studentWebsocket.onMessage(session, JSON.toJSONString(websocketInfo));
    }

}
