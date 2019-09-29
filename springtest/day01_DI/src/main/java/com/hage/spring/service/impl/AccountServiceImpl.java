package com.hage.spring.service.impl;


import com.hage.spring.service.IAccountService;

import java.util.Date;


public class AccountServiceImpl implements IAccountService {

    private String name;
    private int age;
    private Date birthday;

    public AccountServiceImpl(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
        System.out.println("数据保存成功"+name+"-"+age+"-"+birthday);
    }

}
