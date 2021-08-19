package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
//开启Swagger，用于接口测试
@EnableSwagger2
@RequestMapping("/user")
public class UserController {
    @Autowired
    // 从IOC容器中注入UserMapper
    private UserMapper userMapper;

    /**
     * 查询所有用户信息
     */
    @GetMapping("/queryAll")
    public List<User> queryAll() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    /**
     * 根据id查询用户信息
     */
    @GetMapping("query")
    public User queryById(Integer id) {
        return userMapper.selectByPrimaryKey(id);

    }

    /**
     * 添加用户
     */
    @PutMapping("/insert")
    public int insert(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        User user = new User();
        user.setName(name);
        user.setUserAge(age);
        return userMapper.insertUseGeneratedKeys(user);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据id删除用户
     */
    @DeleteMapping("/delete")
    public int deleteById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

}
