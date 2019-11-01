package com.hage.springboot02config.config;

import com.hage.springboot02config.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Administrator
 * @date: 2019/10/31
 * Description:
 */
@Configuration
public class MyConfig {
    @Bean
    public HelloService createService(){
        return new HelloService();
    }
}
