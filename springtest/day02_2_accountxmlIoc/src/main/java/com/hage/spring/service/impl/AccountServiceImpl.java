package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccoutDao;
import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:
 */
public class AccountServiceImpl implements IAccountService {

    private IAccoutDao accoutDao;

    public void setAccoutDao(IAccoutDao accoutDao) {
        this.accoutDao = accoutDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accoutDao.findAllAccount();
    }

    @Override
    public Account findAccountById(int id) {
        return accoutDao.findAccountById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accoutDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accoutDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(int id) {
        accoutDao.deleteAccount(id);
    }
}
