package com.hage;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String eTlString = ETLUtils.getETlString(value.toString());
        if (StringUtils.isBlank(eTlString))
            return;
        k.set(eTlString);
        context.write(k, NullWritable.get());

    }
}
