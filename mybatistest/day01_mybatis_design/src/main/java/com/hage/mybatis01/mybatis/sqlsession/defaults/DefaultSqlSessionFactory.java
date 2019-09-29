package com.hage.mybatis01.mybatis.sqlsession.defaults;

import com.hage.mybatis01.mybatis.cfg.Configuration;
import com.hage.mybatis01.mybatis.sqlsession.SqlSession;
import com.hage.mybatis01.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory的接口实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    Configuration config = new Configuration();
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.config = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(config);
    }
}
