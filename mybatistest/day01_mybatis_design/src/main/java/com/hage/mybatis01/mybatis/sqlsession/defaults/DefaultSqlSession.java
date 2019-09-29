package com.hage.mybatis01.mybatis.sqlsession.defaults;

import com.hage.mybatis01.mybatis.cfg.Configuration;
import com.hage.mybatis01.mybatis.sqlsession.SqlSession;
import com.hage.mybatis01.mybatis.sqlsession.proxy.MapperProxy;
import com.hage.mybatis01.mybatis.utils.DataSourceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession的实现类
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration config;
    private Connection connection;

    public DefaultSqlSession(Configuration config) {
        this.config = config;
        this.connection = DataSourceUtil.getConnection(config);
    }

    /**
     * 用于创建代理对象
     *
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(), new Class[]{daoInterfaceClass}, new MapperProxy(config.getMappers(), connection));
    }


    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
