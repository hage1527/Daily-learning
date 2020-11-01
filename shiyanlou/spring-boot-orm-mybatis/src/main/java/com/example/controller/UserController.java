package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020/10/31
 * Description:
 */
@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> users = userMapper.findAll();
        return users;
    }

    @GetMapping("/findById")
    public User findById(Integer id){
        User user = userMapper.findById(id);
        return user;
    }

    @PostMapping("/insert")
    public String insert(User user){
        Integer add = userMapper.insert(user);
        return "插入成功，插入的数据为"+userMapper.findById(user.getId());
    }

    @PutMapping("/update")
    public Integer update(Integer id,String username){
        Integer update = userMapper.update(id, username);
        return update;
    }

    @DeleteMapping("/delete")
    public String delete(Integer id){
        userMapper.delete(id);
        return "删除成功";
    }



}
