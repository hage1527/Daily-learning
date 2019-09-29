package com.hage.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class SequenceFileDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取job对象
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //设置运行java包路径
        job.setJarByClass(SequenceFileDriver.class);
        //关联mapper类和reducer类
        job.setMapperClass(SequenceFileMapper.class);
        job.setReducerClass(SequenceFileReducer.class);
        //设置Mapper输出类型和最终输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //设置inputformat类
        job.setInputFormatClass(WholeFileInputformat.class);
        //设置outputformat类
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        //设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //提交job
        boolean b = job.waitForCompletion(true);
        System.exit(b? 0:1);

    }
}
