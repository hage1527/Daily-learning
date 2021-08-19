package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
// 开启Swagger2，测试接口
@EnableSwagger2
@RequestMapping("/user")
public class UserController {
    @Autowired
    // 从容器中注入UserMapper
    private UserMapper userMapper;

    //分页查询返回查询后的数据
    @GetMapping("/queryReturnUser")
    public List<User> queryReturnUser(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        //pageNum页码，pageSize每页显示的内容
        PageHelper.startPage(pageNum, pageSize);
        //紧跟着的第一个select方法会被分页,后续的不会
        List<User> users = userMapper.selectAll();
        return users;
    }

    // 分页查询结果封装成PageInfo
    @GetMapping("/queryReturnPageInfo")
    public PageInfo queryReturnPageInfo(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        //pageNum页码，pageSize每页显示的内容
        PageHelper.startPage(pageNum, pageSize);
        //紧跟着的第一个select方法会被分页,后续的不会
        List<User> users = userMapper.selectAll();
        // 查询数据集封装成 PageInfo
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }

}
