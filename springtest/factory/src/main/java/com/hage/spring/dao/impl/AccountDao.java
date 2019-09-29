package com.hage.spring.dao.impl;

import com.hage.spring.dao.IAccountDao;

public class AccountDao implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("数据保存成功");
    }
}
