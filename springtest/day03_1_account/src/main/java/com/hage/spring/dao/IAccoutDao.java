package com.hage.spring.dao;

import com.hage.spring.domain.Account;

import java.util.List;

/**
 * User: Administrator
 * Date: 2019/9/20
 * Time: 19:17
 * Description:数据访问层接口
 */
public interface IAccoutDao {
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
     * 根据名称查询用户
     * @param name
     * @return
     */
    Account findAccountByName(String name);

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




}
