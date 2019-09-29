package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.Account;
import com.hage.mybatis01.entity.AccountUser;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

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
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one = @One(select = "com.hage.mybatis01.dao.IUserDao.findById",fetchType = FetchType.EAGER))

    })
    public List<Account> findAll();

    @Select("select * from account where uid = #{uid}")
    public List<Account> findByUid(Integer uid);


}
