package com.hage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Administrator
 * @date: 2019/10/14
 * Description:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 拦截器
     * @return
     */
    @RequestMapping(value = "/testInterceptor")
    public String testInterceptor()  {
        System.out.println("testInterceptor执行了。。。。");

        return "success";
    }
}
