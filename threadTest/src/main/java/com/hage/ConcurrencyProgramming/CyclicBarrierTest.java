package com.hage.ConcurrencyProgramming;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: Administrator
 * @date: 2019/11/17
 * Description: 用CyclicBarrier实现多个线程一起启动
 */
public class CyclicBarrierTest implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTest(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();//等待barrier变为0,所有线程一起启动
            System.out.println(Thread.currentThread().getName() + "启动时间：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int count = 10;
        CyclicBarrier barrier = new CyclicBarrier(count);
        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierTest(barrier)).start();
        }
    }
}
