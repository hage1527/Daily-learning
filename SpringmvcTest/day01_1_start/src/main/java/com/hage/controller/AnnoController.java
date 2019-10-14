package com.hage.controller;


import com.hage.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;


/**
 * @author: Administrator
 * @date: 2019/9/30
 * Description: 常用注解
 */
@Controller
@RequestMapping(path = "/anno")
@SessionAttributes(value = "msg")
public class AnnoController {
    /**
     * RequestParam注解
     *
     * @param username
     * @return
     */
    @RequestMapping(path = "/requestParam")
    public String testRequestParam(@RequestParam(value = "user") String username) {
        System.out.println(username);
        return "success";
    }

    /**
     * RequestParam注解
     *
     * @param body
     * @return
     */
    @RequestMapping(path = "/requestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println(body);
        return "success";
    }

    /**
     * PathVariable注解
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(value = "id") String id) {
        System.out.println(id);
        return "success";
    }

    /**
     * RequestHeader注解,获取请求头的值
     *
     * @param accept
     * @return
     */
    @RequestMapping(path = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String accept) {
        System.out.println(accept);
        return "success";
    }

    /**
     * CookieValue注解
     * @param cookie
     * @return
     */
    @RequestMapping(path = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookie) {
        System.out.println(cookie);
        return "success";
    }
    /**
     * ModelAttribute注解
     * @return
     */
    @RequestMapping(value="/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        System.out.println("testModelAttribute执行了...");
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public void showUser(String uname, Map<String,User> map){
        System.out.println("showUser执行了...");
        // 通过用户查询数据库（模拟）
        User user = new User();
        user.setName(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }

    /**
     * SessionAttributes的注解
     * @return
     */
    @RequestMapping(value="/testSessionAttributes")
    public String testSessionAttributes(Model model){
        System.out.println("testSessionAttributes...");
        // 底层会存储到request域对象中
        model.addAttribute("msg","美美");
        return "success";
    }

    /**
     * 获取值
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes...");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    /**
     * 清除
     * @param status
     * @return
     */
    @RequestMapping(value="/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status){
        System.out.println("delSessionAttributes...");
        status.setComplete();
        return "success";
    }
}
