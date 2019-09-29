package com.hage.hbase_hdfs;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ReadFromHDFSMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //读取一行数据切分取得rowkey，列族列信息
        String[] values = value.toString().split("\t");
        String rowKey = values[0];
        String name = values[1];
        String color = values[2];
        //切分后的字段封装到put，作为value输出
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("color"), Bytes.toBytes(color));
        //将row封装为key写出
        ImmutableBytesWritable k = new ImmutableBytesWritable(Bytes.toBytes(rowKey));

        context.write(k, put);


    }
}
