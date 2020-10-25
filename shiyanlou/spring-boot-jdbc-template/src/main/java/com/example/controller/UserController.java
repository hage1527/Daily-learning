package com.example.controller;


import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 模拟JdbcTemplate 执行用户操作
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 从容器中根据类型注入 JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有用户信息
     * @return
     */
    @GetMapping("/select")
    public String select(){
        String selectSql= "select id,username,age,gender from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectSql);
        return maps.toString();
    }
    /**
     * 添加用户
     * @return
     */
    @GetMapping("/add")
    public String addUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("小白");
        user.setAge(18);
        user.setGender("男");
        String insertSql = "insert into user(id,username,age,gender) values (?,?,?,?)";
        jdbcTemplate.update(insertSql,
                user.getId(),
                user.getUsername(),
                user.getAge(),
                user.getGender());
        return "插入成功";
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String deleteString = "delete from user where id = ?";
        jdbcTemplate.update(deleteString,id);
        return "删除成功";
    }

    /**
     * 根据id更新用户
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id){
        User user = new User();
        user.setUsername("大虎");
        user.setId(id);
        String updateSql="update user set" +
                " username = ? where id =? ";
        jdbcTemplate.update(updateSql,user.getUsername(),user.getId());
        return "更新成功";
    }






}
