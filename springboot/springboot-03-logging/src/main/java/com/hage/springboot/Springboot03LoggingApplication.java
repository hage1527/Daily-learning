package com.hage.springboot;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot03LoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03LoggingApplication.class, args);
        Log log = LogFactory.getLog(Springboot03LoggingApplication.class);
        //日志的级别
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

}
