package com.hage.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description: 测试spring容器
 */
public class test {
    @Test
    public void findALL(){
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAccountService service = ApplicationContext.getBean("accountService", IAccountService.class);
        service.findAll();
    }
}
