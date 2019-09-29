package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccountDao;
import com.hage.spring.dao.impl.AccountDao;
import com.hage.spring.service.IAccountService;


public class AccountServiceImpl implements IAccountService {



    private IAccountDao accountDao = new AccountDao();
    public AccountServiceImpl(){
        System.out.println("service初始化");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }

    public void  init(){
        System.out.println("对象初始化了。。。");
    }
    public void  destroy(){
        System.out.println("对象销毁了。。。");
    }
}
