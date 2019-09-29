package com.hage.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReduce extends Reducer<Text, FlowBean, Text, FlowBean> {
    FlowBean flowBean = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //1类加
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (FlowBean value : values) {
            sumUpFlow = sumUpFlow + value.getUpFlow();
            sumDownFlow = sumDownFlow + value.getDownFlow();
        }
        flowBean.set(sumUpFlow, sumDownFlow);
        context.write(key, flowBean);
    }
}
