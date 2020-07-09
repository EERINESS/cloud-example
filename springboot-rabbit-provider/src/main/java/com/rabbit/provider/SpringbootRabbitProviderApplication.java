package com.rabbit.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.rabbit.provider.mapper")
public class SpringbootRabbitProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitProviderApplication.class, args);
    }

}
