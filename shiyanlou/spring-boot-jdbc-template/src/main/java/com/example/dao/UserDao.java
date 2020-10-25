package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: Administrator
 * @date: 2020/10/24
 * Description:
 */
@Repository
public class UserDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;


}
