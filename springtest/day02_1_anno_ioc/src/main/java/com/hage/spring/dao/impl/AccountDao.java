package com.hage.spring.dao.impl;

import com.hage.spring.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository(value = "accountDao1")
public class AccountDao implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("账户1数据保存成功");
    }
}
