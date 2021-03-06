package com.hage.Producer;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InterceptorProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "hadoop100:9092");
        //确认机制
        props.put("acks", "all");
        //消息发送最大尝试次数
        props.put("retries", 0);
        //一批消息处理大小
        props.put("batch.size", 16384);
        //增加服务端请求延时
        props.put("linger.ms", 1);
        //发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        //key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //采用自定义分区，都写到分区0
        props.put("partitioner.class", "com.hage.Producer.MyPartitioner");
        //构建拦截器
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("com.hage.interceptor.TimeInterceptor");
        interceptors.add("com.hage.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>("second", Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null) {
                        System.out.println(metadata.partition() + "----" + metadata.offset());
                    }
                } });
                producer.close();

    }
}