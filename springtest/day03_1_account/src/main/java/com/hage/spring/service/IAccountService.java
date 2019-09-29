package com.hage.spring.service;

import com.hage.spring.domain.Account;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description: account服务层接口
 */
public interface IAccountService {
    /**
     * 查找所有
     * @return 所有数据
     */
    List<Account> findAllAccount();

    /**
     * 根据id查找Account
     * @param id
     * @return
     */
    Account findAccountById(int id);

    /**
     * 保存用户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 修改用户
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除用户
     * @param id
     */
    void deleteAccount(int id);

    /**
     * 转账
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transferAccount(String sourceName,String targetName,float money);


}
