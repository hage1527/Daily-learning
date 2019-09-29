package com.hage.spring.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
public class Logger {

    /**
     * 用于打印日志：计划让其在切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    public void before(){
        System.out.println("Logger类中的before方法开始记录日志了。。。");
    }
    /**
     * 用于打印日志：计划让其在切入点方法执行之后执行
     */
    public void afterReturning(){
        System.out.println("Logger类中的afterReturning方法开始记录日志了。。。");
    }
    /**
     * 用于打印日志：计划让其在切入点方法抛出异常时执行
     */
    public void afterThrowing(){
        System.out.println("Logger类中的afterThrowing方法开始记录日志了。。。");
    }
    /**
     * 最终通知
     */
    public void after(){
        System.out.println("Logger类中的after方法开始记录日志了。。。");
    }
    /**
     * 环绕通知
     */
    public Object around(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            before();
            proceed = pjp.proceed();
            afterReturning();
        }catch (Exception e){
            afterThrowing();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            after();
            return  proceed;
        }


    }

}
