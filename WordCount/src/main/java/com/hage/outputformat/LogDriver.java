package com.hage.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取job对象
        Configuration configuration =new Configuration();
        Job job = Job.getInstance(configuration);
        //指定java包路径
        job.setJarByClass(LogDriver.class);
        //关联mapper类和reduce类
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReduce.class);
        //设置map输出key、value、类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置reduce输出的key、value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //设置outputformat类
        job.setOutputFormatClass(LogOutputFormat.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0:1);
    }
}
