package com.hage.mybatis01.dao;

import com.hage.mybatis01.entity.User;
import com.hage.mybatis01.mybatis.annotations.Select;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.omg.CORBA.INTERNAL;

import java.util.List;


public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
