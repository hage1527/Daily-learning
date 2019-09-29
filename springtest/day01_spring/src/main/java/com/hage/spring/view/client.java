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
        //根据id获取对象
        IAccountService accountService = (IAccountService) applicationContext.getBean("accountService");
        accountService.saveAccount();
        System.out.println(accountService);

        IAccountService accountService1 = (IAccountService) applicationContext.getBean("accountService");
        accountService1.saveAccount();
        System.out.println(accountService1);

//        //--------BeanFactory---------- 多例。。有点问题
//        Resource resource = new ClassPathResource("bean.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        IAccountService as  = (IAccountService)factory.getBean("accountService");
//        System.out.println(as);
//        IAccountService as1  = (IAccountService)factory.getBean("accountService");
//        System.out.println(as1);

    }
}
