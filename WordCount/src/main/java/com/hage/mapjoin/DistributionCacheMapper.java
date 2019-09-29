package com.hage.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DistributionCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    HashMap<String, String> hashMap = new HashMap<>();
    Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取输入字节流
        FileInputStream fis = new FileInputStream("f:/hadoop/reducejoin/pd.txt");
        //获取转换流
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        //缓冲流
        BufferedReader reader = new BufferedReader(isr);
        //逐行读取数据
        String line = null;
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            String[] fields = line.split("\t");
            hashMap.put(fields[0], fields[1]);
        }
        //逐级关闭流
        reader.close();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //逐行切分
        String[] fields = value.toString().split("\t");
        String pid = fields[1];
        //通过pid获取name
        String str = value.toString() + "\t" + hashMap.get(pid);
        k.set(str);
        context.write(k, NullWritable.get());

    }
}
