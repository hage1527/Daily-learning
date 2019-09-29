package com.hage.spring.dao;

import com.hage.spring.domain.Account;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/25
 * Description: 账户的持久层接口
 */
public interface IAccountDao {

    /**
     * 根据Id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
