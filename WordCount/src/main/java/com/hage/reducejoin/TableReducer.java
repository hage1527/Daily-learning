package com.hage.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TableReducer extends Reducer<Text, Bean, NullWritable, Bean> {
    ArrayList<Bean> orderBeans = new ArrayList<>();
    Bean pdBean = new Bean();

    @Override
    protected void reduce(Text key, Iterable<Bean> values, Context context) throws IOException, InterruptedException {
        for (Bean table:values) {
            if (table.getFlag().equals("order")){
                // 拷贝传递过来的每条订单数据到集合中
                Bean temp = new Bean();
                try {
                    BeanUtils.copyProperties(temp,table);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orderBeans.add(temp);
            }else {
                //拷贝过来的产品表到内存中
                try {
                    BeanUtils.copyProperties(pdBean, table);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //表的连接
        for (Bean orderBean:orderBeans){
            //数据写出
            orderBean.setPid(pdBean.getPname());
            context.write(NullWritable.get(), orderBean);
        }
    }
}
