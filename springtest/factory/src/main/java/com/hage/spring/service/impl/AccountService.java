package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccountDao;
import com.hage.spring.dao.impl.AccountDao;
import com.hage.spring.factory.BeanFactory;
import com.hage.spring.service.IAccountService;

public class AccountService implements IAccountService {

//    private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");

    private IAccountDao accountDao = new AccountDao();
    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
