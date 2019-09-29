package com.hage.mybatis01.mybatis.sqlsession.proxy;

import com.hage.mybatis01.mybatis.cfg.Configuration;
import com.hage.mybatis01.mybatis.cfg.Mapper;
import com.hage.mybatis01.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection connection) {
        this.mappers = mappers;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        String key = className+"."+methodName;
        //获取Mappers中的mapper对象
        Mapper mapper = mappers.get(key);
        if(mapper == null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        return new Executor().selectList(mapper, connection);
    }
}
