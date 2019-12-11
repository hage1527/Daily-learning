package com.hage;

/**
 * @author: Administrator
 * @date: 2019/11/13
 * Description:单例模式饿汉式
 */
public class Singleton {
    //在类初始化时就创建类对象
    private static Singleton singleton= new Singleton();
    private Singleton(){}//构造函数设为私有时，就不能在别的类调用类的默认构造函数
    public static Singleton getInstance(){//注意获取实例的方法要设为静态方法
        return singleton;
    }

    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }
}
class SingletonTest{
    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
    }

}
