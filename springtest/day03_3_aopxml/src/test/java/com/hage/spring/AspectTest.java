package com.hage.spring;

import com.hage.spring.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: Administrator
 * @date: 2019/9/23
 * Description:切面测试
 */
public class AspectTest {
    public static void main(String[] args) {
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean.xml");
        IAccountService service = applicationContext.getBean("accountService", IAccountService.class);
        service.deleteAccount();
    }
}
