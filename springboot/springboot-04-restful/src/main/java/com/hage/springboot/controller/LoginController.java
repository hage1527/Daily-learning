package com.hage.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2019/11/3
 * Description: 登录控制器
 */
@Controller
public class LoginController {
//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String Password,
                        Map<String,Object> map,
                        HttpSession session) {
        if(!username.isEmpty()&&"123456".equals(Password))
        {
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        else {
            map.put("msg","用户名密码错误");
            return  "login";
        }

    }
}
