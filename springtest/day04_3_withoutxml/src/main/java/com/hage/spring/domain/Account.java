package com.hage.spring.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Administrator
 * @date: 2019/9/25
 * Description: Account实体类
 */
@Getter
@Setter
@ToString
public class Account {

    private Integer id;
    private String name;
    private float money;
}
