package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.QueryVo;
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
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 根据传入参数条件查询 有可能是用户名，有可能是性别 有可能是地址
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);


}
