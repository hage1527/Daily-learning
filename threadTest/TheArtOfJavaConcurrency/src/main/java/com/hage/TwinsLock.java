package com.hage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: Administrator
 * @date: 2019/11/22
 * Description: 自定义同步组件
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);
    // 静态内部类，自定义同步器
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count);
        }
        //共享式获取同步状态
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount <0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }
        //共享式释放同步状态
        @Override
        protected boolean tryReleaseShared(int reduceCount) {
            for (;;){
                int current = getState();
                int newCount = current + reduceCount;
                if (newCount <0 || compareAndSetState(current, newCount)){
                    return true;
                }
            }

        }
    }

    @Override
    public void lock() {
          sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}


class TwinsLockTest{

    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for(int i =0;i<10;i++){
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        for(int i =0;i<10;i++){
            try {
                Thread.sleep(100);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
