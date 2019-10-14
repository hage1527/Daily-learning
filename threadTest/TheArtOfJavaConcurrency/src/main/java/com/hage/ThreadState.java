package com.hage;

import com.hage.utils.SleepUtils;

/**
 * @author: Administrator
 * @date: 2019/10/7
 * Description: 线程的状态
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaiting Thread").start();
        new Thread(new Waiting(), "waiting thread").start();
        new Thread(new Blocked(), "blockthread1").start();
        new Thread(new Blocked(), "blockthread2").start();

    }

    /**
     * 该线程不断的进行睡眠
     */
    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.Class实例上等待,释放锁
     */
    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * 该线程在Blocked.Class实例上加锁，不会释放该锁
     */
    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
