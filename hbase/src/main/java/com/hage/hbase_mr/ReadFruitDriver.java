package com.hage.hbase_mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class ReadFruitDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取hbase配置信息-包含hadoop配置信息
        Configuration configuration = HBaseConfiguration.create();
        Job job = Job.getInstance(configuration);
        //绑定驱动类
        job.setJarByClass(ReadFruitDriver.class);

        //设置Mapper
        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob("fruit", scan, ReadFruitMapper.class, ImmutableBytesWritable.class, Put.class, job);
        //设置reducer
        TableMapReduceUtil.initTableReducerJob("fruit_mr", ReadFruitReducer.class, job);
        //设置reducertask个数
        job.setNumReduceTasks(1);

        boolean b = job.waitForCompletion(true);
        System.out.println(b ? 0:1);

    }
}
