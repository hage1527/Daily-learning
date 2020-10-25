package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Administrator
 * @date: 2020/10/15
 * Description: 泛型原理
 */
public class MyList<T> {
    private T a;
    public T get(){
        return a;
    }
    public void set(T t){
        a=t;
    }
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        list.set(1);
        Integer t = list.get();
        System.out.println(t);
    }
}
