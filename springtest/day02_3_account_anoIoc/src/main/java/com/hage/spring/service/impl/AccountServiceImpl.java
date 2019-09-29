package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccoutDao;
import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:
 */
@Service(value = "accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccoutDao accoutDao;

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
