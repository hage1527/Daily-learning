package com.hage.spring.JdbcTemplate;


import com.hage.spring.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Administrator
 * @date: 2019/9/25
 * Description: 测试类
 */
public class JdbcTemplate1 {

    private ApplicationContext ac;
    private IAccountService service;


    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("bean.xml");
        service = ac.getBean("accountService", IAccountService.class);

    }


    @Test
    public void findById() {
        service.findAccountById(1);
    }

    @Test
    public void transferAccount() {
        service.transferAccount("aaa", "bbb",100f);
    }
}
