package com.hage.spring.proxy;

/**
 * @author: Administrator
 * @date: 2019/9/23
 * Description:
 */
public class IProducerImpl implements IProducer {
    @Override
    public void saleProduct(float money) {
        System.out.println("销售产品，拿到钱"+money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("售后服务，拿到钱"+money);
    }
}
