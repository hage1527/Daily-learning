package com.example.controller;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Map;

/**
 * 模拟JdbcTemplate 执行用户操作
 */
@EnableSwagger2
@RestController
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 从容器中根据类型注入JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询所有用户信息
     */
    @GetMapping("/queryAll")
    public String queryAll() {
        String selectSql = "select id,name,age from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectSql);
        return maps.toString();
    }

    /**
     * 根据id查询用户信息
     */
    @GetMapping("/queryById")
    public String queryById(int id) {
        String selectSql = "select id,name,age from user where id = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectSql, id);
        return maps.toString();
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public String addUser(User user) {
        String insertSql = "insert into user(id,name,age) values (?,?,?)";
        jdbcTemplate.update(insertSql,
                user.getId(),
                user.getName(),
                user.getAge());
        return "插入成功,插入的数据为" + this.queryById(user.getId());
    }

    /**
     * 根据id删除用户
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String deleteString = "delete from user where id = ?";
        jdbcTemplate.update(deleteString, id);
        return "删除成功";
    }

    /**
     * 根据id更新用户
     */
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, String name) {
        String updateSql = "update user set" +
                " name = ? where id =? ";
        jdbcTemplate.update(updateSql, name, id);
        return "更新成功，更新后的数据为" + this.queryById(id);
    }

}
