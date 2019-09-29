package com.hage.spring.dao.impl;

import com.hage.spring.dao.IAccountDao;
import org.springframework.stereotype.Repository;

@Repository(value = "accountDao2")
public class AccountDao1 implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("账户2数据保存成功");
    }
}
