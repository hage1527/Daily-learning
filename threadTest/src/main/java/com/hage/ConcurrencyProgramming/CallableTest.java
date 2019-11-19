package com.hage.ConcurrencyProgramming;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: Administrator
 * @date: 2019/11/17
 * Description: 实现callable接口创建线程
 */
public class CallableTest implements Callable {
    @Override
    //可以有返回值，也可以抛出异常
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "执行了";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTest callableTest = new CallableTest();
        FutureTask futureTask = new FutureTask<>(callableTest);
        Thread thread = new Thread(futureTask);
        thread.start();
        //通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果
        System.out.println(futureTask.get());

    }
}
