package com.hage.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class LogRecordWriter extends RecordWriter<Text, NullWritable> {
    private FileSystem fileSystem = null;
    private FSDataOutputStream hageOut = null;
    private FSDataOutputStream othersOut = null;

    public LogRecordWriter(TaskAttemptContext job) {
        Configuration configuration = job.getConfiguration();
        try {
            //获取文件系统
            fileSystem = FileSystem.get(configuration);
            //创建日志文件输出路径
            Path hagePath = new Path("f:/hadoop/hage.txt");
            Path otherPath = new Path("f:/hadoop/others.txt");
            hageOut = fileSystem.create(hagePath);
            othersOut = fileSystem.create(otherPath);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String line = key.toString();
        if (line.contains("hage")){
            hageOut.write(line.getBytes());
        }else{
            othersOut.write(line.getBytes());
        }

    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(hageOut);
        IOUtils.closeStream(othersOut);
        fileSystem.close();
    }
}
