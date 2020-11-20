package com.hage.ThreadLocal;



/**
 * @author: Administrator
 * @date: 2020/11/14
 * Description:
 */
public class TestThreadLocal {

    private ThreadLocal<Long>  longLocal = new ThreadLocal<Long>();
    private ThreadLocal<String>  stringLocal = new ThreadLocal<String>();

    public void set() {
        this.longLocal.set(Thread.currentThread().getId());
        this.stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLongLocal() {
        return longLocal.get();
    }

    public String getStringLocal() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadLocal threadLocal = new TestThreadLocal();
        threadLocal.set();
        System.out.println(threadLocal.getLongLocal());
        System.out.println(threadLocal.getStringLocal());
        Thread thread = new Thread(()->{
            threadLocal.set();
            System.out.println(threadLocal.getLongLocal());
            System.out.println(threadLocal.getStringLocal());
        });
        thread.start();
        thread.join();
        System.out.println(threadLocal.getLongLocal());
        System.out.println(threadLocal.getStringLocal());
    }


}
