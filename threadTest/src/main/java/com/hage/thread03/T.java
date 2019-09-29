package com.hage.thread03;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * cn: 透明的，临时的
 * <p>
 * JMM(Java Memory Model)：
 * 在JMM中，所有对象以及信息都存放在主内存中（包含堆、栈）
 * 而每个线程都有自己的独立空间，存储了需要用到的变量的副本，
 * 线程对共享变量的操作，都会在自己的工作内存中进行，然后同步给主内存
 * <p>
 * 下面的代码中，running 是位于堆内存中的t对象的
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都会去读取堆内存，
 * 这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 */
public class T {
    volatile boolean running = true;

    void m() {
        System.out.println("m.start");
        while (running) {

        }
        System.out.println("m.stop");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.m()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;

    }
}
