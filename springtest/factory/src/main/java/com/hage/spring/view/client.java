package com.hage.spring.view;

import com.hage.spring.factory.BeanFactory;
import com.hage.spring.service.IAccountService;
import com.hage.spring.service.impl.AccountService;

public class client {

    public static void main(String[] args) {
        IAccountService accountService = (IAccountService) BeanFactory.getBean("accountService");
        accountService.saveAccount();
        System.out.println(accountService);
        IAccountService accountService1 = (IAccountService) BeanFactory.getBean("accountService");
        accountService1.saveAccount();
        System.out.println(accountService1);

    }
}
