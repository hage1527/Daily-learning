package com.hage.thread02;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 * 子类调用父类的同步方法，是否也是可重入的？
 * 答：可重入的
 */
public class T {

    synchronized void m() throws InterruptedException {
        System.out.println("m start");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("m stop");
    }

    public static void main(String[] args) {
        TT tt = new TT();
        try {
            tt.m();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TT extends T {
    synchronized void m() throws InterruptedException {
        System.out.println("child m start");
        super.m();
        System.out.println("child m stop");
    }
}