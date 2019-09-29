package com.hage.orderbean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    /**
     *000001  pd_1  555
     * 000001  pd_2  222
     * 000002  pd_3  333
     */
    // 订单id
    private  int orderId;
    private  double price;

    public OrderBean() {
    }

    public OrderBean(int orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId=" + orderId +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(OrderBean o) {
        int result;
        if (this.orderId > o.orderId)
            result = -1;
        else if (this.orderId < o.orderId)
            result = 1;
        else {
            result = this.price > o.getPrice()?  -1:1;
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(orderId);
        out.writeDouble(price);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        orderId = in.readInt();
        price = in.readDouble();

    }
}
