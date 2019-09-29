package com.hage.spring.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author: Administrator
 * @date: 2019/9/22
 * Description: 事务管理工具类包括开始事务，提交事务，回滚事务和释放连接
 */
@Component(value = "transactionUtil")
public class TransactionUtil {

    @Resource(name = "connectionUtil")
    private ConnectionUtil connectionUtil;
    /**
     * 开始事务
     */
    public void beginTransaction(){
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public void commitTransaction(){
        try {
            connectionUtil.getThreadConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public void rollbackTransaction(){
        try {
            connectionUtil.getThreadConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放连接
     */
    public void release(){
        connectionUtil.removeConnection();
    }




}
