package com.hage.spring.service.impl;

import com.hage.spring.service.IAccountService;

import java.util.*;

/**
 * User: Administrator
 * Date: 2019/9/18
 * Time: 21:25
 */
public class AccountServiceImpl3 implements IAccountService {
    private String[] mystr;
    private List<String> mylist;
    private Map<String,String> mymap;
    private Set<String> myset;
    private Properties myprop;

    public void setMystr(String[] mystr) {
        this.mystr = mystr;
    }

    public void setMylist(List<String> mylist) {
        this.mylist = mylist;
    }

    public void setMymap(Map<String, String> mymap) {
        this.mymap = mymap;
    }

    public void setMyset(Set<String> myset) {
        this.myset = myset;
    }

    public void setMyprop(Properties myprop) {
        this.myprop = myprop;
    }

    @Override
    public void saveAccount() {
        System.out.println("数据保存成功");
        System.out.println(Arrays.toString(mystr));
        System.out.println(mylist);
        System.out.println(mymap);
        System.out.println(myset);
        System.out.println(myprop);

    }
}
