package com.hage.dao;

import com.hage.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description:
 */
@Repository("accountDao")
public interface IAccountDao {
    /**
     * 查找所有账户
     * @return
     */
    @Select("select * from account_spring")
    public List<Account> findAll();

    /**
     * 保存账户
     * @param account
     */
    @Insert("insert into account_spring (name,money) values(#{name},#{money})")
    public void savaAccount(Account account);
}
