package com.hage.writablecompare;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text,FlowBean,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //切分value为单个字符
        String line = value.toString();
        String[] strings = line.split("\t");
        //获取手机号，上行流量，下行流量
        String phone = strings[0];
        long upflow = Long.parseLong(strings[strings.length-2]);
        long downflow = Long.parseLong(strings[strings.length-1]);
        FlowBean flowBean= new FlowBean(upflow, downflow);
        //写出到collector
        context.write(flowBean, new Text(phone));
        


    }
}
