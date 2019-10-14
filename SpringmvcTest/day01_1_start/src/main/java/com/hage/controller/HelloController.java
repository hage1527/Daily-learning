package com.hage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Administrator
 * @date: 2019/9/29
 * Description: 控制器类
 */
@Controller
@RequestMapping(path = "/user")
public class HelloController {

    /**
     * 入门案例
     *
     * @return
     */
    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello springMVC");
        return "success";
    }

    /**
     * 测试RequestMapping注解
     *
     * @return
     */
    @RequestMapping(value = "/testRequestMapping", params = "username=hehe", headers = {"Accept"})
    public String testRequsetMapping() {
        System.out.println("测试RequestMapping注解。。。");
        return "success";
    }
}
