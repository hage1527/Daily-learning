package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {

    // 从容器中根据类型获取UserMapper
    @Autowired
    private UserMapper userMapper;

    // 查询所有用户信息
    @GetMapping("/findAll")
    public List<User> findAll() {
        List<User> users = userMapper.findAll();
        return users;
    }

    // 根据id查询用户信息
    @GetMapping("/findById")
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }

    // 插入用户
    @PostMapping("/insert")
    public String insert(User user) {
        Integer add = userMapper.insert(user);
        return "插入成功，插入的数据为" + userMapper.findById(user.getId());
    }

    // 根据id更新用户
    @PutMapping("/update")
    public String update(Integer id, String username) {
        Integer update = userMapper.update(id, username);
        return "更新成功后的数据为" + userMapper.findById(id);
    }

    // 根据id更新
    @DeleteMapping("/delete")
    public String delete(Integer id) {
        userMapper.delete(id);
        return "删除成功";
    }

}
