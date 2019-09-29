package com.hage.hbase_hdfs;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;

import java.io.IOException;

public class ReadFromHDFSReducer extends TableReducer<ImmutableBytesWritable, Put,ImmutableBytesWritable> {

    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        for (Put value : values) {
            context.write(key, value);
        }
    }
}
