package com.hage.spring.service.impl;

import com.hage.spring.service.IAccountService;

import java.util.Date;

/**
 * User: Administrator
 * Date: 2019/9/18
 * Time: 21:09
 * 使用set方法注入
 */
public class AccountServiceImpl2 implements IAccountService {
    private String name;
    private int age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
        System.out.println("数据保存成功"+name+"-"+age+"-"+birthday);
    }
}
