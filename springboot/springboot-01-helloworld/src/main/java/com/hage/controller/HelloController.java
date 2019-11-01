package com.hage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Administrator
 * @date: 2019/10/17
 * Description:
 */
//@Controller
//@ResponseBody
@RestController

public class HelloController {
//    @ResponseBody
    @RequestMapping("/hello")
    public String  hello(){
        return "Hello World";
    }
}
