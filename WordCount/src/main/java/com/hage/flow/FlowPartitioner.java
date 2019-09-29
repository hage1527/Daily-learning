package com.hage.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowPartitioner extends Partitioner <Text, FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        int partID = 3;
        String phoneID= text.toString().substring(0, 3);
        if (phoneID.equals("111"))
            partID = 0;
        else if(phoneID.equals("222"))
            partID = 1;
        else if(phoneID.equals("333"))
            partID = 2;

        return partID;
    }
}
