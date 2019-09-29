package com.hage.reverseindex;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    String name;
    IntWritable v = new IntWritable(1);
    Text k = new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //通过上下文获取切片信息
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        //通过切片信息获取文件名称
        name = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        for (String word:words){
            String str = word+"--"+name;
            k.set(str);
            context.write(k, v);
        }
    }
}
