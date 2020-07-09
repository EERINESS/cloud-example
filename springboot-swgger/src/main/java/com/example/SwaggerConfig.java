package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zzq on 2020/6/5.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.example";


    @Bean
    public Docket empApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .build()
                .apiInfo(empApiInfo());
    }

    private ApiInfo empApiInfo() {
        return new ApiInfoBuilder()
                .title("eMP iSM Core API")
                .description("eMP 融合调度核心服务 API")
                .contact(new Contact("houmong.rd", "http://www.houmong.com", "rd@houmong.com"))
                .termsOfServiceUrl("http://www.houmong.com")
                .licenseUrl("http://www.houmong.com")
                .build();
    }
}
