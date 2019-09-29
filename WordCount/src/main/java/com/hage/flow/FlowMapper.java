package com.hage.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {


    FlowBean flowBean = new FlowBean();
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.获取第一行
        String line = value.toString();
        //2.单词切分
        String[] fields = line.split("\t");//?为什么一用空格分就出错呢
        //3.电话号码
        String phone = fields[1];
        //4.上行流量，下行流量
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);
        //5 封装对象
        flowBean.set(upFlow, downFlow);
        //6 写出
        k.set(phone);
        context.write(k, flowBean);

    }
}
