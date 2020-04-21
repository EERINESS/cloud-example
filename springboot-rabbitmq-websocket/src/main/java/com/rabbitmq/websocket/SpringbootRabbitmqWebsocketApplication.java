package com.rabbitmq.websocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rabbitmq.websocket.mapper")
public class SpringbootRabbitmqWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqWebsocketApplication.class, args);
    }

}
