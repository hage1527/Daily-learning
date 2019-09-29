package com.hage.hbase_hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReadFromHDFSDriver extends Configured implements Tool {

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = this.getConf();
        Job job = Job.getInstance(conf);

        job.setJarByClass(ReadFromHDFSDriver.class);

        job.setMapperClass(ReadFromHDFSMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);

        TableMapReduceUtil.initTableReducerJob("fruit_hdfs", ReadFromHDFSReducer.class, job);

        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job, new Path(strings[0]));

        boolean result= job.waitForCompletion(true);

        return  result ? 0:1;
    }

    public static void main(String[] args) throws Exception {

        Configuration configuration = HBaseConfiguration.create();
        int run = ToolRunner.run(configuration, new ReadFromHDFSDriver(), args);
        System.out.println(run);
    }
}
