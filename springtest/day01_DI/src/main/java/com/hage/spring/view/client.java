package com.hage.spring.view;

import com.hage.spring.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class client {

    public static void main(String[] args) {
        //1.获取核心容器对象----单例
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //根据构造函数获取对象
        IAccountService accountService = (IAccountService) applicationContext.getBean("accountService");
        accountService.saveAccount();
        System.out.println(accountService);
        //set方法注入
        IAccountService accountService2 = (IAccountService) applicationContext.getBean("accountService2");
        accountService2.saveAccount();
        System.out.println(accountService2);

        IAccountService accountService3 = (IAccountService) applicationContext.getBean("accountService3");
        accountService3.saveAccount();
        System.out.println(accountService3);
    }
}
