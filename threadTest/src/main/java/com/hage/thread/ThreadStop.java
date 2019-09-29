package com.hage.thread;

import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.Scanner;

/**
 * 此类用于演示线程的停止
 * 1.stop 已经过时
 * 2.Interrupt 只能中断睡眠、等待这些状态，会抛出InterruptedException异常,并没有真正的结束线程
 * 3.采用通知的方法 ★
 * <p>
 * 步骤1：让需要停止的线程中添加一个循环标记，默认值为true
 * 步骤2：让需要停止的线程中添加一个公共的set方法，用于更新循环标记
 * 步骤3：需要停止该线程时，调用set方法即可
 */
public class ThreadStop {
    public static void main(String[] args) {

        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2(myThread1);
        myThread1.start();
        myThread2.start();

    }
}

class MyThread1 extends Thread {
    boolean flag = true;
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Math.random()*100+"----");
        }
    }
}

class MyThread2 extends Thread{
    Scanner in = new Scanner(System.in);
    MyThread1 thread1 = null;

    public MyThread2( MyThread1 thread1) {
        this.thread1 = thread1;
    }
    @Override
    public void run() {
        while (true){
            System.out.println("请输入：");
            char key = in.next().toUpperCase().charAt(0);
            if (key=='Q'){
                thread1.setFlag(false);
                break;
            }
        }
    }
}
