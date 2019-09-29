package com.hage.stream;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "logfilter");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop100:9092");
        //构建拓扑
        TopologyBuilder builder = new TopologyBuilder();

        builder.addSource("SOURCE", "first").addProcessor("PROCESS", (ProcessorSupplier<byte[], byte[]>) () -> new LogProcessor(), "SOURCE").addSink("SINK", "second", "PROCESS");
        //创建kafkastream
        KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);
        kafkaStreams.start();
    }
}
