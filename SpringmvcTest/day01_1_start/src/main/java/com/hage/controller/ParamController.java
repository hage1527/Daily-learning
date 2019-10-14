package com.hage.controller;

import com.hage.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Administrator
 * @date: 2019/9/30
 * Description: 请求参数绑定控制器
 */
@Controller
@RequestMapping(path = "/param")
public class ParamController {
    /**
     * 基本数据类型绑定
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(path = "/testParam")
    public String testParam(String username, String password) {
        System.out.println("用户名:" + username);
        System.out.println("密码:" + password);
        return "success";
    }

    /**
     * 包装类绑定
     *
     * @param account
     * @return
     */
    @RequestMapping(path = "/saveAccount")
    public String saveUser(Account account) {
        System.out.println(account);
        return "success";
    }

    /**
     * 包装类中含list和map绑定
     *
     * @param account
     * @return
     */
    @RequestMapping(path = "/saveListAndMap")
    public String saveListAndMap(Account account) {
        System.out.println(account);
        return "success";
    }
}
