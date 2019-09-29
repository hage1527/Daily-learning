package com.hage.spring.service.impl;

import com.hage.spring.dao.IAccoutDao;
import com.hage.spring.domain.Account;
import com.hage.spring.service.IAccountService;
import com.hage.spring.utils.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:
 */
@Service(value = "accountServiceTransaction")
public class AccountServiceTransactionImpl implements IAccountService {

    @Autowired
    private IAccoutDao accoutDao;

    @Resource(name = "transactionUtil")
    private TransactionUtil transactionUtil;


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

    @Override
    public void transferAccount(String sourceName, String targetName, float money) {
        System.out.println("余额转出中");

        try {
            transactionUtil.beginTransaction();
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
            transactionUtil.commitTransaction();
        }catch (Exception e){
            transactionUtil.rollbackTransaction();
            e.printStackTrace();
        }finally {
            transactionUtil.release();
        }


    }
}
