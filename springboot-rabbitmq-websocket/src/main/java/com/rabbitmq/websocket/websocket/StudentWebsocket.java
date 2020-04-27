package com.rabbitmq.websocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.entity.WebReturn;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zzq on 2020/4/27.
 */
@Log
@ServerEndpoint(value = "/student")
@Component
public class StudentWebsocket {

    @Value("${server.port}")
    String port;

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<StudentWebsocket> websocketSet = new CopyOnWriteArraySet<>();

    //用来记录userId和该session进行绑定
    public static Map<String, Map<String, Object>> map = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(){
        websocketSet.add(this); // 加入Set中
        log.info(String.format("有新连接加入, 当前连接数: %d", websocketSet.size()));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        websocketSet.remove(this);  // 从 set 中删除
        log.info(String.format("有一连接关闭, 当前连接数: %d", websocketSet.size()));
    }

    /**
     * 信息有更改推送前端
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message){
        log.info("port : " + port);
        log.info(message);
        ObjectMapper objectMapper = new ObjectMapper();
        WebReturn webReturn;
        try {
            webReturn = objectMapper.readValue(message, WebReturn.class);
            if (webReturn.getCode() == 202){
                //发送给前端消息
                session.getAsyncRemote().sendText(webReturn.getData().toString());
            }else if (webReturn.getCode() == 101){
                Map<String, String> map1  = (Map<String, String> )webReturn.getData();
                Map<String, Object> mapObj = new HashMap<>();
                mapObj.put("session", session);
                mapObj.put("schoolId", map1.get("schoolId"));
                map.put(map1.get("userId"), mapObj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable error){
        log.info(String.format("发生错误 : + %s", session.getId()));
        error.printStackTrace();
    }
}
