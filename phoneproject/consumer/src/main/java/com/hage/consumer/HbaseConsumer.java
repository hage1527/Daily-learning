package com.hage.consumer;

import com.hage.utils.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Properties;

public class HbaseConsumer {

    public static void main(String[] args) throws IOException{
        Properties properties = PropertiesUtil.getProperties();
        //创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList("calllog"));
        HBaseDAO hBaseDAO = new HBaseDAO();
        //循环拉取数据并打印
        try {
            while (true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.value());
                    hBaseDAO.put(consumerRecord.value());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            hBaseDAO.close();

        }

    }
}
