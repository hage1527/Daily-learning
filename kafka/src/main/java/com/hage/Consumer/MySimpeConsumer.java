package com.hage.Consumer;

import com.sun.javafx.image.impl.IntArgbPre;
import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 低级消费者api
 */
public class MySimpeConsumer {
    public static void main(String[] args) {
        //
        ArrayList<String> brokers = new ArrayList<String>();
        brokers.add("hadoop100");
        brokers.add("hadoop101");
        brokers.add("hadoop102");
        //端口号
        int port = 9092;
        //topic
        String topic = "second";
        //分区号
        int partition = 0;
        //偏移量
        long offset = 0;
        MySimpeConsumer simpeConsumer = new MySimpeConsumer();
        PartitionMetadata leader = simpeConsumer.getLeader(brokers, port, topic, partition);
        System.out.println(leader);
        simpeConsumer.getData(brokers, port, topic, partition, offset);


    }

    private void getData(ArrayList<String> brokers, int port, String topic, int partition, long offset) {
        PartitionMetadata partitionMetadata = this.getLeader(brokers, port, topic, partition);
        //获取指定分区的leader
        String leader = partitionMetadata.leader().host();
        //获取consumer对象
        SimpleConsumer client = new SimpleConsumer(leader, port, 1000, 1024 * 4, "client" + topic);
        //拉取数据请求
        FetchRequest build = new FetchRequestBuilder().clientId("client" + topic).addFetch(topic, partition, offset, 1000).build();
        //拉取数据
        FetchResponse fetchResponse = client.fetch(build);
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);
        for (MessageAndOffset messageAndOffset : messageAndOffsets) {
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);

            System.out.println("offset" + messageAndOffset.offset()
                    + "----" + new String(bytes));

        }

    }

    //获取分区leader
    private PartitionMetadata getLeader(ArrayList<String> brokers, int port, String topic, int partition) {
        SimpleConsumer consumer = null;
        for (String broker : brokers) {
            consumer = new SimpleConsumer(broker, port, 1000, 1024 * 4, "client");
            //获取topic元数据
            //消费者发送获取topic元数据请求获得topic元数据信息
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));
            TopicMetadataResponse topicMetadataResponse = consumer.send(topicMetadataRequest);

            List<TopicMetadata> topicMetadata = topicMetadataResponse.topicsMetadata();
            for (TopicMetadata topicMetadatum : topicMetadata) {
                //获取topic中所有的分区元数据信息
                List<PartitionMetadata> partitionMetadata = topicMetadatum.partitionsMetadata();
                for (PartitionMetadata partitionMetadatum : partitionMetadata) {
                    if (partitionMetadatum.partitionId() == partition)
                        return partitionMetadatum;
                }
            }
        }
        consumer.close();
        return null;
    }


}
