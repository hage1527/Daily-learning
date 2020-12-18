package com.example.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

/**
 * @author: Administrator
 * @date: 2020/12/18
 * Description:
 */
@Table(name = "user")
@Data
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer userId;
    private String userName;
    private Integer userAge;
}
