package com.hage.spring.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author: Administrator
 * @date: 2019/9/22
 * Description: 事务管理工具类包括开始事务，提交事务，回滚事务和释放连接
 * 这里采用aop配置
 */
@Component(value = "transactionUtil")
@Aspect
public class TransactionUtil {
    @Pointcut(value = "execution(* *..AccountServiceImpl.*(..))")
    private void pc(){}

    @Resource(name = "connectionUtil")
    private ConnectionUtil connectionUtil;
    //采用前置通知，后置通知，异常通知，最后通知的机制时，后三个的顺序会乱序，所有建议采用环绕通知
    /**
     * 开始事务
     */
//    @Before(value = "pc()")
    public void beginTransaction(){
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
            System.out.println("自动提交为false");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("pc()")
    public void commitTransaction(){
        try {
            connectionUtil.getThreadConnection().commit();
            System.out.println("提交事务");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("pc()")
    public void rollbackTransaction(){
        try {
            connectionUtil.getThreadConnection().rollback();
            System.out.println("回滚事务");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放连接
     */
//    @After("pc()")
    public void release(){
        connectionUtil.removeConnection();
        System.out.println("关闭连接");
    }
     @Around("pc()")
     public Object transaction(ProceedingJoinPoint pjp){
         Object rstValue = null;
         try {
             beginTransaction();
             rstValue = pjp.proceed();
             commitTransaction();
         } catch (Throwable throwable) {
             rollbackTransaction();
             throwable.printStackTrace();
         } finally {
             release();
             return rstValue;
         }
     }




}
