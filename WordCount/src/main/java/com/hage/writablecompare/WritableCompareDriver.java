package com.hage.writablecompare;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WritableCompareDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            //1获取job
            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration);
            //2设置java路径
            job.setJarByClass(WritableCompareDriver.class);
            //3关联mapper类和Reduce类
            job.setMapperClass(FlowMapper.class);
            job.setReducerClass(FlowReducer.class);
            //4关联输入输出类型
            job.setMapOutputKeyClass(FlowBean.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(FlowBean.class);
            job.setOutputValueClass(Text.class);
            //5设置输入输出路径
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            //6提交任务
            boolean b = job.waitForCompletion(true);
            System.exit(b ? 0 : 1);
    }
}
