package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
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
    public List<User> queryAll(){
        List<User> users = userMapper.selectAll();
        return users;
    }

    @GetMapping("query")
    public User queryById(Integer id){
        return  userMapper.selectByPrimaryKey(id);

    }


    @PutMapping("/insert")
    public int insert(@RequestParam("name") String name, @RequestParam("age") Integer age){
        User user = new User();
        user.setUserName(name);
        user.setUserAge(age);
        return userMapper.insertUseGeneratedKeys(user);
    }
    @PostMapping("/update")
    public int update(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @DeleteMapping("/delete")
    public int deleteById(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }


}
