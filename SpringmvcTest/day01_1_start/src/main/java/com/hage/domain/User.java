package com.hage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: Administrator
 * @date: 2019/9/30
 * Description: 用户类
 */
@Getter
@Setter
@ToString
public class User {
    private String name;
    private Integer age;
    private Date  date;
}
