package com.hage.thread;

/**
 * 此类用于演示线程创建并启动方式的区别
 * 案例：三个售票窗口卖票
 */
public class ThreadStartDiff {
    public static void main(String[] args) {
//        //方式一
//        SellTicket sellTicket = new SellTicket();
//        sellTicket.start();
//        SellTicket sellTicket1 = new SellTicket();
//        sellTicket1.start();
//        SellTicket sellTicket2 = new SellTicket();
//        sellTicket2.start();
        //方式二
        SellTicket2 st = new SellTicket2();
        new Thread(st).start();
        new Thread(st).start();
        new Thread(st).start();

    }
}
//方式一
class SellTicket extends Thread{
    int tickets = 100;
    @Override
    public void run() {
       while (true){
           if (tickets<0){
               System.out.println("票已卖完");
               break;
           }
           System.out.println(Thread.currentThread().getName()+"------"+"所剩票数为"+(--tickets));
       }
    }
}
//方式二
class SellTicket2 implements  Runnable{
    int tickets = 100;
    @Override
    public void run() {
        while (true){
            if (tickets<0){
                System.out.println("票已卖完");
                break;
            }
            System.out.println(Thread.currentThread().getName()+"------"+"所剩票数为"+(--tickets));
        }
    }
}