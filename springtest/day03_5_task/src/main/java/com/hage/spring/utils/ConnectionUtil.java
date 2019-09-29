package com.hage.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: Administrator
 * @date: 2019/9/22
 * Description:连接的工具类，从数据源中获取一个连接并和线程绑定
 */
@Component(value = "connectionUtil")
public class ConnectionUtil {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取线程上的连接
     * @return
     */
    public Connection getThreadConnection(){
        //从线程上获取连接
        Connection connection = threadLocal.get();
        //判断连接是否为空
        if (connection==null) {
            try {
                //重新新建连接绑定到线程上
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 把线程和连接解绑
     */
    public void removeConnection(){
        threadLocal.remove();
    }
}
