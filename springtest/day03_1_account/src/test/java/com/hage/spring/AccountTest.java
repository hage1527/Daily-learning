package com.hage.spring;

import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import com.hage.spring.utils.TransactionUtil;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description: 通过对每个方法提供事务支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountTest {

    @Resource(name = "accountServiceTransaction")
    private IAccountService as;
    @Resource(name = "transactionUtil")
    private TransactionUtil transactionUtil;

    @Test
    public void testFindAll() {
        List<Account> allAccount = as.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindAllById() {
        Account account = as.findAccountById(2);
        System.out.println(account);

    }

    @Test
    public void testTransferAccount() {
        as.transferAccount("ccc", "bbb", 100f);
    }
}
