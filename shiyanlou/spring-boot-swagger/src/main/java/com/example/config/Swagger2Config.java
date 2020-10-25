package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2) //文档类型，选择SWAGGER_2即可
                // 配置映射路径
                .pathMapping("/")
                // 扫描Controller包路径，获得Api接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any())
                // 配置Api接口信息
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Spring Boot 整合 Swagger")
                        .description("文档描述信息")
                        .version("1.0")
                        .contact(new Contact("shiyanlou", "https://www.lanqiao.cn/", "shiyanlou.com"))// 联系人
                        .license("The Apache License, Version 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .build()
                );
        return docket;
    }
}

