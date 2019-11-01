package com.hage.controller;

import com.hage.exception.SysException;
import com.hage.exception.SysExceptionResolver;
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


    @RequestMapping(value = "/testException")
    public String testException() throws SysException {
        System.out.println("testException执行了。。。。");
        try {
            //模拟异常
            int i = 10 / 0;
        }catch (Exception e){
            //打印异常
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("查询所有用户出现错误了...");
        }

        return "success";
    }
}
