package com.example.websocket.controller;

import com.example.websocket.entity.SocketMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by zzq on 2020/4/16.
 * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
 * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
 */
@ServerEndpoint(value = "/websocket/{nickname}")
@Component
public class MyWebsocket {
    // 用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebsocket> websocketSet = new CopyOnWriteArraySet<>();
    //用来记录sessionId和该session进行绑定
    private static Map<String, Session> map = new HashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String nickname;

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname){
        this.session = session;
        this.nickname = nickname;
        map.put(session.getId(), session);
        websocketSet.add(this); // 加入Set中
        System.out.println("有新连接加入:" + nickname + ", 当前在线人数为：" + websocketSet.size());
        //this.session.getAsyncRemote().sendText("恭喜" + nickname + "成功连接上WebSocket(其频道号：" + session.getId() + ")-->当前在线人数为：" + websocketSet.size());
        broadcast("恭喜" + nickname + "成功连接上WebSocket(其频道号：" + session.getId() + ")-->当前在线人数为：" + websocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        websocketSet.remove(this);  // 从 set 中删除
        System.out.println("有一连接关闭！当前在线人数为" + websocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("nickname") String nickname){
        System.out.println("来自客户端的消息-->" + nickname + ": " + message);
        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象，
        // 然后通过socketMsg的type进行判断是单聊还是群聊，进行相应的处理:
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;

        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if(socketMsg.getType() == 1){
                //单聊.需要找到发送者和接受者.
                socketMsg.setFromUser(session.getId());//发送者.
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                //发送给接受者.
                if(toSession != null){
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(nickname+"："+socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname+"："+socketMsg.getMsg());
                }else{
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
                }
            }else{
                //群发消息
                broadcast(nickname+": "+socketMsg.getMsg());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

//        // 群发消息
//        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * @param message
     */
    public void broadcast(String message){
        for (MyWebsocket item : websocketSet){
            item.session.getAsyncRemote().sendText(message);    // 异步发送消息
        }
    }
}
