package com.hage.service;

import com.hage.domain.Account;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description:
 */
public interface IAccountService {
    /**
     * 查找所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户
     * @param account
     */
    public void saveAccount(Account account);
}
