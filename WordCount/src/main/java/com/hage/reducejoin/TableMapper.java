package com.hage.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, Bean> {
    private Bean table = new Bean();
    private Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取数据文件名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        String fname = inputSplit.getPath().getName();
        //value切分
        String[] fields = value.toString().split("\t");
        //封装bean类
        if (fname.startsWith("order")){
            table.setId(fields[0]);
            table.setPid(fields[1]);
            table.setPname("");
            table.setAmount(Integer.parseInt(fields[2]));
            table.setFlag("order");
            k.set(fields[1]);
        }else{
            table.setPid(fields[0]);
            table.setPname(fields[1]);
            table.setId("");
            table.setAmount(0);
            table.setFlag("product");
            k.set(fields[0]);
        }
        //context写出
        context.write(k, table);
    }
}
