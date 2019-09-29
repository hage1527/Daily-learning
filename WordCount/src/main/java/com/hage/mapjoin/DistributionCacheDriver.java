package com.hage.mapjoin;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DistributionCacheDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        //1获取job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //2设置java路径
        job.setJarByClass(DistributionCacheDriver.class);
        //3关联mapper类
        job.setMapperClass(DistributionCacheMapper.class);

        //4关联输入输出类型
        //因没有reduce，所有不用设置map输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //5设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.addCacheFile(new URI("file:///f:/hadoop/reducejoin/pd.txt"));
        //  map端join的逻辑不需要reduce阶段，设置reducetask数量为0
        job.setNumReduceTasks(0);

        //6提交任务
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
