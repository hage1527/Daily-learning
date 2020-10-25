package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Administrator
 * @date: 2020/9/12
 * Description:
 */
@RestController
public class UserController {
    /**
     * 创建线程安全的Map,模拟数据存储
     */
    static ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/users/adduser")
    public String addUser(User user) {
        users.put(user.getId(), user);
        return "添加成功";
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/users/getuser")
    public User getUser(Integer id) {
        User user = users.get(id);
        return user;
    }

    /**
     * 验证@PathVariable和@RequestParam作用
     *
     * @param pathvariable ：路径变量
     * @param requestparam ：请求参数
     * @return
     */
    @RequestMapping("/getparams/{pathvariable}")
    public String getParams(@PathVariable String pathvariable, @RequestParam("requestparam") String  requestparam) {

        return "PathVariable的值为:"+pathvariable+"，RequestParam的值为:"+requestparam;
    }

}
