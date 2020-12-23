package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/12/18
 * Description:
 */
@RestController
@EnableSwagger2
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryAll")
    public List<User> queryAll(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectAll();
        return users;
    }

    @GetMapping("/queryByOffSet")
    public List<User> queryByOffSet(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize){
        PageHelper.offsetPage(pageNum,pageSize);
        List<User> users = userMapper.selectAll();
        return users;
    }





}
