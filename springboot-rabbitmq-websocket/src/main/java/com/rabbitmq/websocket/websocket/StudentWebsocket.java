package com.rabbitmq.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.websocket.entity.WebReturn;
import com.rabbitmq.websocket.service.RedisService;
import com.rabbitmq.websocket.util.SpringUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzq on 2020/4/27.
 */
@Log
@ServerEndpoint(value = "/student")
@Component
public class StudentWebsocket {

    private StringRedisTemplate stringRedisTemplate = SpringUtils.getBean(StringRedisTemplate.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisService redisService;

    @Value("${server.port}")
    String port;
    public static final String WEBSOCKET_SESSION = "websocketSession";

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
        Map<String, Map<String, Object>> map = JSON.parseObject(stringRedisTemplate.opsForValue().get("websocketSession"), Map.class);
        map.remove(session.getId());
//        stringRedisTemplate.opsForValue().set("websocketSession", JSON.toJSONString(map));
        redisService.set(WEBSOCKET_SESSION, JSON.toJSONString(map));
        log.info("有一连接关闭");
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
        try {
            WebReturn webReturn = objectMapper.readValue(message, WebReturn.class);
            if (webReturn.getCode() == 202){
                //发送给前端消息
                session.getAsyncRemote().sendText(webReturn.getData().toString());
            }else if (webReturn.getCode() == 101){
                log.info("sessionId : " + session.getId());
                Map<String, Map<String, Object>> map;
                if (redisService.get(WEBSOCKET_SESSION) != null){
                    map = JSON.parseObject(redisService.get(WEBSOCKET_SESSION), Map.class);
                }else{
                    map = new HashMap<>();
                }
                Map<String, Object> mapObj = new HashMap<>();
                mapObj.put("session", session);
                mapObj.put("schoolId", webReturn.getData());
                map.put(session.getId(), mapObj);
                redisService.set(WEBSOCKET_SESSION, JSON.toJSONString(map));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
//    @OnMessage
//    public void onMessage(Session session, String message){
//        log.info("port : " + port);
//        log.info(message);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            WebReturn webReturn = objectMapper.readValue(message, WebReturn.class);
//            if (webReturn.getCode() == 202){
//                //发送给前端消息
//                session.getAsyncRemote().sendText(webReturn.getData().toString());
//            }else if (webReturn.getCode() == 101){
//                log.info("sessionId : " + session.getId());
//                Map<String, Map<String, Object>> map;
//                if (stringRedisTemplate.opsForValue().get("websocketSession") != null){
//                    map = JSON.parseObject(stringRedisTemplate.opsForValue().get("websocketSession"), Map.class);
//                }else{
//                    map = new HashMap<>();
//                }
//                Map<String, Object> mapObj = new HashMap<>();
//                mapObj.put("session", session);
//                mapObj.put("schoolId", webReturn.getData());
//                map.put(session.getId(), mapObj);
//                stringRedisTemplate.opsForValue().set("websocketSession", JSON.toJSONString(map));
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    @OnError
    public void onError(Session session, Throwable error){
        log.info(String.format("发生错误 : + %s", session.getId()));
    }
}
