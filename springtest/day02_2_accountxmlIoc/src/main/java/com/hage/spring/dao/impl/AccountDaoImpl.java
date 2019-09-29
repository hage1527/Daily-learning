package com.hage.spring.dao.impl;

import com.hage.spring.dao.IAccoutDao;
import com.hage.spring.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description:账户的持久层实现类
 */
public class AccountDaoImpl implements IAccoutDao {

    private QueryRunner queryRunner;

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            return queryRunner.query("select * from account_spring", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Account findAccountById(int id) {
        try {
            return queryRunner.query("select * from account_spring where id = ?", new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            queryRunner.update("insert into account_spring (name,money) values (?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            queryRunner.update("update account_spring set name=?,money=? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAccount(int id) {
        try {
            queryRunner.update("delete from account_spring where id =?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
