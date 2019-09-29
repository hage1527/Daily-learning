package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccountDao;
import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:
 */
@Service(value = "accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accoutDao;



    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public void findAccountById(int id) {
        Account account = accoutDao.findAccountById(id);
        System.out.println(account);
    }


    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Override
    public void transferAccount(String sourceName, String targetName, float money) {
        System.out.println("余额转出中");
        //1.获取转出账户
        Account source = accoutDao.findAccountByName(sourceName);
        //2.获取转入账户
        Account target = accoutDao.findAccountByName(targetName);
        //3.转出账户减钱
        source.setMoney(source.getMoney()-money);
        //4.转入账户加钱
        target.setMoney(target.getMoney()+money);
        //5.更新转出账户
        accoutDao.updateAccount(source);
        int i=1/0;
        //6.更新转入账户
        accoutDao.updateAccount(target);
    }
}
