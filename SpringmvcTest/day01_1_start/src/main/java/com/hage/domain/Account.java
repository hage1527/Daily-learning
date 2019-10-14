package com.hage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2019/9/30
 * Description:
 */
@Getter
@Setter
@ToString
public class Account {
    private Double money;
    //    private User user;
    private List<User> list;
    private Map<String, User> map;
}
