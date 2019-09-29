package com.hage.mr;

import com.hage.kv.CommDimension;
import com.hage.kv.CountDurationValue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountDurationReducer extends Reducer<CommDimension, Text, CommDimension, CountDurationValue> {
    private int counter;
    private int durations;
    private CountDurationValue v = new CountDurationValue();
    @Override
    protected void reduce(CommDimension key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        counter = 0;
        for (Text value : values) {
            String duration = value.toString();
            counter++;
            durations += Integer.valueOf(duration);
        }
        v.setCount(counter);
        v.setDuration(durations);
        context.write(key, v);
    }
}
