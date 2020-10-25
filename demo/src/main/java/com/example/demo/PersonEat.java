package com.example.demo;

import java.util.ServiceLoader;

/**
 * @author: Administrator
 * @date: 2020/10/13
 * Description:
 */
public class PersonEat implements Eat {
    @Override
    public void eat() {
        System.out.println("人一天不吃饿得慌");
    }

    public static void main(String[] args) throws Exception{
        ServiceLoader<Eat> Eats = ServiceLoader.load(Eat.class);
        for (Eat eat : Eats) {
            eat.eat();
//            eat.wait();
            eat.notify();
        }

    }

}
