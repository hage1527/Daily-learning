package com.hage.orderbean;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1获取job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //2设置java路径
        job.setJarByClass(OrderDriver.class);
        //3关联mapper类和Reduce类
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);
        //4关联输入输出类型
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        //5设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //设置groupingComparator类
        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        //6提交任务
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
