package com.rabbitmq.websocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.AppUtil;
import com.rabbitmq.websocket.entity.WebReturn;
import com.rabbitmq.websocket.service.CacheService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

/**
 * Created by zzq on 2020/4/27.
 */
@Log
@ServerEndpoint(value = "/student")
@Component
public class StudentWebsocket {

    //CacheService cacheService = AppUtil.applicationContext.getBean(CacheService.class);
    public static Map<String, Map<String, Object>> sessionMap = new HashMap<>();

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
        //cacheService.deleteCacheBySession(session.getId());
        sessionMap.remove(session.getId());
    }

    /**
     * 信息有更改推送前端
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message){
        log.info(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WebReturn webReturn = objectMapper.readValue(message, WebReturn.class);
            if (webReturn.getCode() == 202){
                //发送给前端消息
                session.getBasicRemote().sendText(webReturn.getData().toString());
            }else if (webReturn.getCode() == 101){
                log.info("sessionId : " + session.getId());
                Map<String, Object> mapObj = new HashMap<>();
                mapObj.put("session", session);
                mapObj.put("schoolId", webReturn.getData());
                //cacheService.saveCache(session.getId(), mapObj);
                sessionMap.put(session.getId(), mapObj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable error){
        log.info(String.format("发生错误 : + %s", session.getId()));
    }

}
