package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户表
 */
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String gender;
}
