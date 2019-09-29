package com.hage.spring.cglib;

/**
 * @author: Administrator
 * @date: 2019/9/23
 * Description:
 */
public class Producer {

    public void saleProduct(float money) {
        System.out.println("销售产品，拿到钱"+money);
    }

    public void afterService(float money) {
        System.out.println("售后服务，拿到钱"+money);
    }
}
