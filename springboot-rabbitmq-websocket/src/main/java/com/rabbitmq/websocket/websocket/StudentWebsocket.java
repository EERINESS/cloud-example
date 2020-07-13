package com.rabbitmq.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.AppUtil;
import com.rabbitmq.websocket.entity.WebsocketInfo;
import lombok.extern.java.Log;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zzq on 2020/4/27.
 */
@Log
@ServerEndpoint(value = "/student")
@Component
public class StudentWebsocket {

    StringRedisTemplate stringRedisTemplate = AppUtil.applicationContext.getBean(StringRedisTemplate.class);
    public static Map<String, Session> sessionMap = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(){
        log.info("有新连接加入");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        log.info("有一连接关闭");
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String websocketStr = operations.get("websocket_session");
        List<WebsocketInfo> websocketInfoList;
        if (websocketStr == null) {
           return;
        }else {
            JSONArray jsonArray = JSONArray.parseArray(websocketStr);
            websocketInfoList = jsonArray.toJavaList(WebsocketInfo.class);
            websocketInfoList = websocketInfoList.stream().filter(info -> !session.getId().equals(info.getSessionId())).collect(Collectors.toList());
        }
        operations.set("websocket_session", JSON.toJSONString(websocketInfoList));
        log.info("session : " + session.getId() + " 失去连接，从redis中剔除");
    }

    /**
     * 信息有更改推送前端
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String websocketStr = operations.get("websocket_session");
            List<WebsocketInfo> websocketInfoList;
            if (websocketStr == null) {
                websocketInfoList = new ArrayList<>();
            }else {
                JSONArray jsonArray = JSONArray.parseArray(websocketStr);
                websocketInfoList = jsonArray.toJavaList(WebsocketInfo.class);
            }
            WebsocketInfo websocketInfo = objectMapper.readValue(message, WebsocketInfo.class);
            if (websocketInfo.getType() == 202){
                if (session == null){
                    log.info("session : " + websocketInfo.getSessionId() + " 失去连接，从redis中剔除");
                    websocketInfoList = websocketInfoList.stream().filter(info -> !websocketInfo.getSessionId().equals(info.getSessionId())).collect(Collectors.toList());
                }else {
                    //发送给前端消息
                    session.getBasicRemote().sendText(JSON.toJSONString(websocketInfo.getStudentList()));
                }
            }else if (websocketInfo.getType() == 101){
                log.info("sessionId : " + session.getId());
                websocketInfo.setSessionId(session.getId());
                websocketInfoList.add(websocketInfo);
                sessionMap.put(session.getId(), session);
            }
            operations.set("websocket_session", JSON.toJSONString(websocketInfoList));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable error){
        log.info(String.format("发生错误 : + %s", session.getId()));
    }

}
