package com.hage.service;

import com.hage.dao.IAccountDao;
import com.hage.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description:
 */
@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层：findAll()执行了");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：saveAccount()执行了");
        accountDao.savaAccount(account);
    }
}
