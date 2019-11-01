package com.hage.controller;

import com.hage.domain.Account;
import com.hage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description:
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    /**
     * 测试ssm整合
     * @return
     */
    @RequestMapping("/testFindAll")
    public String testFindAll(){
        System.out.println("testFindAll执行了");
        List<Account> accounts = accountService.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        return "success";
    }

    @RequestMapping("/testSave")
    public String testSave(Account account){
        System.out.println("testSave执行了");
        accountService.saveAccount(account);
        return "success";
    }

}
