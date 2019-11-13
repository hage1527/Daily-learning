package com.hage.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author: Administrator
 * @date: 2019/11/2
 * Description:
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("hello", "nihao");
        return "success";
    }
}
