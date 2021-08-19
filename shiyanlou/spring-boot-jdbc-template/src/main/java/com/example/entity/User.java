package com.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户表
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
