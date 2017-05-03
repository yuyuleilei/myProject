/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.kafka;

import com.yl.core.bean.CustomerBO;
import com.zhixindu.commons.utils.JsonUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/28
 * @description
 */
public class YlKafkaProducer implements Serializable{
    private static final long serialVersionUID = 2609371768436008481L;

    private static final Logger LOG = LoggerFactory.getLogger(YlKafkaProducer.class);
    private static Properties properties = null;

    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("producer.type", "sync");
        properties.put("request.required.acks", "1");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
        properties.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
      properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
      properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public void produce(){
        long t0 = System.currentTimeMillis();
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
//        for (int i = 0; i<10; i++) {
//            kafkaProducer.send(new ProducerRecord<String,String>("yltest", "key1", "value1:"+i + " test1 data1"));
//        }
        CustomerBO customerBO = new CustomerBO();
        customerBO.setName("余磊");
        customerBO.setMobile("17091918167");
        customerBO.setId_no("411524198905111410");
        kafkaProducer.send(new ProducerRecord<String,String>("yltest", "key", JsonUtil.toJsonString(customerBO)));
        kafkaProducer.close();
        System.out.println("Time : " + (System.currentTimeMillis() - t0));
    }

    public static void main(String [] args){
        YlKafkaProducer ylKafkaProducer = new YlKafkaProducer();
        ylKafkaProducer.produce();
    }
}
