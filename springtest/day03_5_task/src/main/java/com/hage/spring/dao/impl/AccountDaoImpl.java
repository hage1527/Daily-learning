package com.hage.spring.dao.impl;

import com.hage.spring.dao.IAccoutDao;
import com.hage.spring.domain.Account;
import com.hage.spring.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:账户的持久层实现类
 */
@Repository(value = "accountDao")
public class AccountDaoImpl implements IAccoutDao {

    @Autowired
    private QueryRunner queryRunner;
    @Resource(name = "connectionUtil")
    private ConnectionUtil connectionUtil;

    @Override
    public List<Account> findAllAccount() {
        try {
            return queryRunner.query(connectionUtil.getThreadConnection(),"select * from account_spring", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Account findAccountById(int id) {
        try {
            return queryRunner.query(connectionUtil.getThreadConnection(),"select * from account_spring where id = ?", new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String name) {
        try {
            return queryRunner.query(connectionUtil.getThreadConnection(),"select * from account_spring where name= ?", new BeanHandler<Account>(Account.class),name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            queryRunner.update(connectionUtil.getThreadConnection(),"insert into account_spring (name,money) values (?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            queryRunner.update(connectionUtil.getThreadConnection(),"update account_spring set name=?,money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAccount(int id) {
        try {
            queryRunner.update(connectionUtil.getThreadConnection(),"delete from account_spring where id =?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
