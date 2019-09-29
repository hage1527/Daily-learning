package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.Role;
import com.hage.mybatis01.entity.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IRoleDao {
    /**
     * 查找所有
     * @return
     */
    List<Role> findAll();






}
