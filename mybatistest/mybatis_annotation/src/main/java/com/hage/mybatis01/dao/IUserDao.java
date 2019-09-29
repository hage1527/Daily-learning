package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查找所有的user并找出相关的account
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),
            @Result(property = "accounts",column = "id",many =@Many(select = "com.hage.mybatis01.dao.IAccountUserDao.findByUid",fetchType = FetchType.EAGER))
    })
    List<User> findAll();

    /**
     * 根据id查询用
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer userId);




}
