package com.hage.ConcurrencyProgramming;


import java.util.concurrent.CountDownLatch;

/**
 * @author: Administrator
 * @date: 2019/11/17
 * Description: 用CountdownLatch实现多个线程一起启动
 */
public class CountdownLatchTest implements Runnable {
    private CountDownLatch countDownLatch;

    public CountdownLatchTest(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();//等待latch变为0,所有线程一起启动
            System.out.println(Thread.currentThread().getName() + "启动时间：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < 10; i++) {
            new Thread(new CountdownLatchTest(latch)).start();
            latch.countDown();
        }
    }
}
