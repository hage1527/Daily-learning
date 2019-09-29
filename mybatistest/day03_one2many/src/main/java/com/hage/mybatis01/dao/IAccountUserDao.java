package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.Account;
import com.hage.mybatis01.entity.AccountUser;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountUserDao {
    /**
     * 一对一
     * 查询所有账户，同时获取账户的所属用户名称及它的地址信息
     * @return
     */
    public List<Account> findAll();


}
