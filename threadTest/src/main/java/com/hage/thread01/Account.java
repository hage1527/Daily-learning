package com.hage.thread01;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，而对业务读方法不加锁，
 * 容易出现 脏读问题
 * <p>
 * 因为，在执行写的过程中，因为读操作没有加锁，所以读会读取到写未改完的脏数据
 * 解决办法，给读写都加锁
 */
public class Account {
    /**
     * 银行账户名称
     */
    String name;
    /**
     * 余额
     */
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(() -> account.set("hage", 200), "threadSet").start();//start之后线程进入了就绪态
        new Thread(() -> account.getBalance(), "threadGet").start();//若不加这一行getbalance的值为0
        System.out.println("balance--" + account.getBalance());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("balance--" + account.getBalance());
    }

}
