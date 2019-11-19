package com.hage.Annotation;

/**
 * @author: Administrator
 * @date: 2019/11/13
 * Description: 延迟加载的单例模式
 */
// 第一种方法 利用volatile关键字
public class DoubleCheckLocking {
    //加volatile禁止指令的重排序
    private  volatile static DoubleCheckLocking instance;

    private DoubleCheckLocking(){ }

    public static DoubleCheckLocking getInstance(){
        if (instance==null) {
            synchronized (DoubleCheckLocking.class){
                if (instance==null){
                    instance = new DoubleCheckLocking();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DoubleCheckLocking instance1 = DoubleCheckLocking.getInstance();
        DoubleCheckLocking instance2 = DoubleCheckLocking.getInstance();
        System.out.println(instance1 == instance2);
    }
}
//第二种方法 基于类的初始化方案
class SingleByClass{
    private SingleByClass(){}
    private static class InstanceHolder{
        private static SingleByClass instance = new SingleByClass();
    }
    private static SingleByClass getInstance(){
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {
        SingleByClass instance = SingleByClass.getInstance();
        SingleByClass instance1 = SingleByClass.getInstance();
        System.out.println(instance == instance1);
    }
}
