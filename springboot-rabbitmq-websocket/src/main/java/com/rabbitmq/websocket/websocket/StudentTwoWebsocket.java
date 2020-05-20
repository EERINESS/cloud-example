package com.rabbitmq.websocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.entity.SocketMsg;
import com.rabbitmq.websocket.entity.WebReturn;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zzq on 2020/5/20.
 */
@Log
//@ServerEndpoint(value = "/student")
//@Component
public class StudentTwoWebsocket {

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<StudentTwoWebsocket> websocketSet = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用来记录sessionId和该session进行绑定
    public static Map<String, Map<String, Object>> map = new HashMap<>();

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
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
     * 收到客户端消息后调用的方法
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        sendMessage(message);
    }

    /**
     * 有 rabbit 订阅的消息收到，发送给前端
     * @param message
     */
    public void sendMessage(String message){
        log.info(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WebReturn webReturn = objectMapper.readValue(message, WebReturn.class);
            if (webReturn.getCode() == 202){
                //发送给前端消息
                session.getAsyncRemote().sendText(webReturn.getData().toString());
            }else if (webReturn.getCode() == 101){
                log.info("sessionId : " + session.getId());
                Map<String, Object> mapObj = new HashMap<>();
                mapObj.put("session", session);
                mapObj.put("schoolId", webReturn.getData());
                //cacheService.saveCache(session.getId(), mapObj);
                map.put(session.getId(), mapObj);
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
