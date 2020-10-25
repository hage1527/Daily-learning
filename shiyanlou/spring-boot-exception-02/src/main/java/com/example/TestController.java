package com.example;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试Controller，用于抛出异常
 */
@Controller
public class TestController {


    @GetMapping("/json")
    @ResponseBody
    public String jsonException() {
        // 抛出异常
        throw new NullPointerException("服务器出错了");
    }



}
