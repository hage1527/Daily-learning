package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查找所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用
     * @param userId
     * @return
     */
    User findById(Integer userId);




}
