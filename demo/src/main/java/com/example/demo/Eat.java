package com.example.demo;

/**
 * @author: Administrator
 * @date: 2020/10/13
 * Description:
 */
public interface Eat {

    default public void eat(){
        System.out.println("eat");
    }

}
