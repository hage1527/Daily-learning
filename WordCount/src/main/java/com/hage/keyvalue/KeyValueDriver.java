package com.hage.keyvalue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class KeyValueDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1获取job对象
        Configuration configuration = new Configuration();
        //设置切割符
        configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR," ");
        Job job = Job.getInstance(configuration);
        //2指定java包路径
        job.setJarByClass(KeyValueDriver.class);
        //3关联mapper类和reduce类
        job.setMapperClass(KeyValueMapper.class);
        job.setReducerClass(KeyValueReduce.class);
        //4设置mapper输出keyvalue类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        //5设置最终输出的KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //6设置fileinputformat类
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        //7设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //8job提交
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 1:0);
    }

}
