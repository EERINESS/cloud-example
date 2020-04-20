package com.rabbitmq.websocket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.entity.SocketMsg;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zzq on 2020/4/20.
 */
@Log
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebsocket {

    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebsocket> websocketSet = new CopyOnWriteArraySet<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        websocketSet.add(this); // 加入Set中
        log.info("成功连接一");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        websocketSet.remove(this);  // 从 set 中删除
        log.info("一连接关闭");
    }

    /**
     * 有 rabbit 订阅的消息收到，发送给前端
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        sendMessage(message);
    }

    public void sendMessage(String message){
        log.info(message);
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;
        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            //发送给前端消息
            //this.session.getBasicRemote().sendText(socketMsg.getMessageData());
            for (MyWebsocket item : websocketSet){
                item.session.getAsyncRemote().sendText(socketMsg.getMessageData());    // 异步发送消息
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
