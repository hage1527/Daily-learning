package com.hage.thread04;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer3 {
    // Count down 往下数  Latch 门闩
    // 门闩不能保证可见性，不是一种同步方式，只是一种线程通信方式，保证不了可见性
    // 门闩的等待，不会持有任何锁
    ArrayList<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer3 myContainer = new MyContainer3();
        CountDownLatch downLatch = new CountDownLatch(1);
        new Thread(() -> {

            if (myContainer.size() != 5) {
                try {
                    downLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("监测到容器长度为5，线程2立即退出");


        }, "thread--2").start();

        new Thread(() -> {

            for (int count = 0; count < 10; count++) {
                myContainer.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("add" + count);
                if (count == 5) {
                    downLatch.countDown();
                }
            }

        }, "thread--1").start();


    }
}
