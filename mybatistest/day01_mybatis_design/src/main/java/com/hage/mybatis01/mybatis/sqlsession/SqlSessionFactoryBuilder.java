package com.hage.mybatis01.mybatis.sqlsession;

import com.hage.mybatis01.mybatis.cfg.Configuration;
import com.hage.mybatis01.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.hage.mybatis01.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建sqlsessionfactory对象
 */
public class SqlSessionFactoryBuilder {
    //根据参数的字节输入流来构建一个SqlSessionFactory工厂
    public SqlSessionFactory build(InputStream config) {
        Configuration configuration = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(configuration);
    }
}
