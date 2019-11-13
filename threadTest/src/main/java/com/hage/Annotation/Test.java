package com.hage.Annotation;

/**
 * @author: Administrator
 * @date: 2019/11/10
 * Description:
 */
public class Test {
    private volatile int i = 0;
    public synchronized void  add(){
            i++;
    }
}
