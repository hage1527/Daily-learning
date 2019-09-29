package com.hage.spring.JdbcTemplate;


import config.SpringConfig;
import com.hage.spring.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: Administrator
 * @date: 2019/9/25
 * Description: 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class JdbcTemplate1 {

    @Autowired
    private IAccountService service;



    @Test
    public void findById() {
        service.findAccountById(1);
    }

    @Test
    public void transferAccount() {
        service.transferAccount("aaa", "bbb",100f);
    }
}
