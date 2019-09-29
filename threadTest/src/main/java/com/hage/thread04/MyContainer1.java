package com.hage.thread04;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer1 {
    /**
     * 给list添加volatile属性，volatile只能保证可见性，不能保证原子性
     */
    volatile ArrayList<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer1 myContainer = new MyContainer1();
        new Thread(() -> {
            for (int count = 0; count < 10; count++) {
                myContainer.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("add" + count);
            }
        }, "thread--1").start();
        new Thread(() -> {
            while (true) {
                if (myContainer.size() == 5) {
                    break;
                }
            }
            System.out.println("监测到容器长度为5，线程2立即退出");
        }, "thread--2").start();
    }
}
