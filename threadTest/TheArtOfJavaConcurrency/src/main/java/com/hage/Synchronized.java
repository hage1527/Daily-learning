package com.hage;

/**
 * @author: Administrator
 * @date: 2019/10/7
 * Description: Synchronized关键字的实现细节
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){
            m();
        }
    }
    public static synchronized void m(){

    }
}
