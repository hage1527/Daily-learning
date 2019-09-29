package com.hage.spring.view;

import com.hage.spring.config.Config;
import com.hage.spring.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
          //使用xml配置
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //使用注册配置配置文件
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);
        accountService.saveAccount();

        IAccountService accountService1 = ac.getBean("accountService", IAccountService.class);
        accountService1.saveAccount();

        System.out.println(accountService == accountService1);
    }
}
