package com.rabbitmq.websocket.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zzq on 2020/4/20.
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @RequestMapping(value = "/sendTopicMessage", method = RequestMethod.GET)
    public String sendTopicMessage(@RequestParam(value ="message") String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", message);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.data", manMap);
        return "ok";
    }

    @RequestMapping(value = "/sendTopicMessage1", method = RequestMethod.GET)
    public String sendTopicMessage1(@RequestParam(value ="message") String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", message);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.da", manMap);
        return "ok";
    }


}
