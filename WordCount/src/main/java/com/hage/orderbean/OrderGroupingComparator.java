package com.hage.orderbean;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *
 */
public class OrderGroupingComparator extends WritableComparator {


    public OrderGroupingComparator() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean orderBean1 = (OrderBean) a;
        OrderBean orderBean2 = (OrderBean) b;
        int result;
        if (orderBean1.getOrderId()> orderBean2.getOrderId())
            result = 1;
        else if (orderBean1.getOrderId() < orderBean2.getOrderId())
            result = -1;
        else  result = 0;
        return result;
    }
}
