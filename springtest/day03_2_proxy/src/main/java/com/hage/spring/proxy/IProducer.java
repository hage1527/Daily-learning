package com.hage.spring.proxy;

/**
 * @author: Administrator
 * @date: 2019/9/23
 * Description: 对生产厂家要求的接口
 */
public interface IProducer {
    /**
     * 销售
     * @param money
     */
     public void saleProduct(float money);

    /**
     * 售后
     * @param money
     */
     public void afterService(float money);
}
