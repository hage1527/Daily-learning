package com.hage.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 相当于yarn客户端，负责提交MapReduce作业
 */
public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1 获取job对象
        Configuration configuration = new Configuration();

        //设置map端输出压缩以及压缩方式
//        configuration.setBoolean("mapreduce.map.output.compress", true);
//        configuration.setClass("mapreduce.map.output.compress.codec", GzipCodec.class, CompressionCodec.class);
        Job job = Job.getInstance(configuration);
        //2 指定java包路径
        job.setJarByClass(WordCountDriver.class);
        //3 关联mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //4 设置mapper输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5 设置reducer输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6 指定job输入数据的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //7 指定job输出数据的路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //空格区分不同的功能，用哪一个把哪一个注释取消即可

        //设置reduce端压缩及压缩方式(map输出压缩和reduce输出压缩不能共用)
        FileOutputFormat.setCompressOutput(job, true);
        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

        //设置inputformat类型及切片大小
        //job.setInputFormatClass(CombineTextInputFormat.class);
        //CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
        //CombineTextInputFormat.setMinInputSplitSize(job, 2097152);

        //设置分区个数,这个需要实现partioner类，根据业务情况设置reducetask数目
        //job.setNumReduceTasks(2);

        //在map后使用combine操作，关联合并类
        //job.setCombinerClass(WordCountCombine.class);

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);


    }
}
