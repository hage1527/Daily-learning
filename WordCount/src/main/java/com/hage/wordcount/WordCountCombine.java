package com.hage.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * combine类也继承自reducer类，是在map端的reduce操作
 * （1）用户自定义的Reducer要继承自己的父类
 * （2）Reducer的输入数据类型对应Mapper的输出数据类型，也是KV
 * （3）Reducer的业务逻辑写在reduce()方法中
 * （4）Reducetask进程对每一组相同k的<k,v>组调用一次reduce()方法
 */
public class WordCountCombine extends Reducer<Text, IntWritable,Text,IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable v : values) {
            //计算单词出现的总次数
            sum = sum + v.get();
        }
        context.write(key, new IntWritable(sum));

    }
}
