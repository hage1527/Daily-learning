package com.hage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: Administrator
 * @date: 2019/10/16
 * Description: 账户实体类
 */
@Getter
@Setter
@ToString
public class Account {
    private int id;
    private String name;
    private double money;
}
