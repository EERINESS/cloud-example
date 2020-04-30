package com.rabbitmq.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by zzq on 2020/4/20.
 */
@Configuration
public class WebSocketConfig {
    /**
     * @Endpoint注解的websocket交给ServerEndpointExporter自动注册管理
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return  new ServerEndpointExporter();
    }
}
