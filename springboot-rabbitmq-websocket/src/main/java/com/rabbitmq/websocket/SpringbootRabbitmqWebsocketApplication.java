package com.rabbitmq.websocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.rabbitmq.websocket.mapper")
@EnableCaching
@EnableEurekaClient
@EnableFeignClients
public class SpringbootRabbitmqWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqWebsocketApplication.class, args);
    }

}
