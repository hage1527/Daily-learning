package com.hage.spring;

import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountTest {

    @Autowired
    private IAccountService as = null;
    @Test
    public void testFindAll(){
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindAllById(){
        Account account = as.findAccountById(2);
        System.out.println(account);

    }
}
