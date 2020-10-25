package com.example.controller;

import com.example.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@Api(tags = "用户管理接口")
public class UserController {
    /**
     * 创建线程安全的Map,模拟数据存储
     */
    static ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();

    @GetMapping("/users")
    @ApiOperation(value = "查询用户列表")
    public String getUsers() {

        return users.toString();
    }

    @PostMapping("/users")
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "user", value = "用户", dataType = "User")
    public String addUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "添加成功";
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "根据id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    public User getUser(@PathVariable("id") Integer id) {

        return users.get(id);
    }

    @PutMapping("/users/{id}")
    @ApiOperation(value = "根据id更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "用户", dataType = "User")
    }
    )
    public String putUser(@PathVariable("id") Integer id, @RequestBody User user) {
        users.put(id, user);
        return "更新后数据为：" + users.get(id);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Integer")
    public String deleteUser(@PathVariable("id") Integer id) {
        users.remove(id);
        return "已经删除id=" + id + "的用户数据";
    }

}