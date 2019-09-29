package com.hage.spring.service;

/**
 * @author: Administrator
 * @date: 2019/9/20
 * Description: account服务层接口
 */
public interface IAccountService {


    /**
     * 根据id查找Account
     * @param id
     * @return
     */
    void findAccountById(int id);


    void transferAccount(String sourceName, String targetName, float money);


}
