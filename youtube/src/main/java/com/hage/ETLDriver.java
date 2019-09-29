package com.hage;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.split.JobSplitWriter;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ETLDriver implements Tool {
    private Configuration configuration = null;

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(configuration);

        job.setJarByClass(ETLDriver.class);

        job.setMapperClass(ETLMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return  job.waitForCompletion(true)? 0:1;

    }

    @Override
    public void setConf(Configuration conf) {
        this.configuration = conf;
    }

    @Override
    public Configuration getConf() {
        return this.configuration;
    }

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new ETLDriver(), args);
        System.out.println(run);
    }
}
