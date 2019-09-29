package com.hage.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WholeRecordReader extends RecordReader<NullWritable, BytesWritable> {
    private FileSplit split;
    private Configuration configuration;

    private boolean progress = false;
    private BytesWritable value = new BytesWritable();

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        this.split = (FileSplit) split;
        configuration = context.getConfiguration();

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!progress) {
            byte[] contents = new byte[(int) split.getLength()];
            FileSystem fs = null;
            FSDataInputStream fsDataInputStream = null;
            //1.从切片信息获得切片中文件所在路径
            Path splitFilePath = split.getPath();
            //2.从文件所在路径与配置信息获得文件系统
            fs = splitFilePath.getFileSystem(configuration);
            //3.文件系统打开包含切片信息的文件
            fsDataInputStream = fs.open(splitFilePath);
            //4.读取文件内容
            IOUtils.readFully(fsDataInputStream, contents, 0, (int) split.getLength());
            //5.输出文件内容
            value.set(contents, 0, contents.length);
            progress = true;
            return true;
        }
        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return progress ? 1 : 0;
    }

    @Override
    public void close() throws IOException {

    }
}
