/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.kafka;

import com.yl.core.app.WebAppConfig;
import com.yl.core.bean.CustomerBO;
import com.zhixindu.commons.utils.JsonUtil;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/28
 * @description
 */
@PropertySource(value = {"classpath:/kafka-producer.properties"})
@Component
public class YlKafkaProducer implements Serializable{
    private static final long serialVersionUID = 2609371768436008481L;

    private static final Logger LOG = LoggerFactory.getLogger(YlKafkaProducer.class);
    private static Properties properties = null;

    @Value("${bootstrap.servers}")
    private String bootstrapServers;
    @Value("${producer.type}")
    private String producerType;
    @Value("${request.required.acks}")
    private String acks;
    @Value("${serializer.class}")
    private String serializerClass;
    @Value("${partitioner.class}")
    private String partitionerClass;
    @Value("${key.serializer}")
    private String keySerializer;
    @Value("${value.serializer}")
    private String valueSerializer;

    public void produce(String topic,Object data){
        properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("producer.type", producerType);
        properties.put("request.required.acks", acks);
        properties.put("serializer.class", serializerClass);
        properties.put("partitioner.class", partitionerClass);
        properties.put("key.serializer", keySerializer);
        properties.put("value.serializer", valueSerializer);
        long t0 = System.currentTimeMillis();
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
        kafkaProducer.send(new ProducerRecord<String,String>(topic, "key", JsonUtil.toJsonString(data)));
        kafkaProducer.close();
        System.out.println("Time : " + (System.currentTimeMillis() - t0));
    }

    public static void main(String [] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class);
        YlKafkaProducer ylKafkaProducer = (YlKafkaProducer)context.getBean("ylKafkaProducer");
        //生产消息
        CustomerBO customerBO = new CustomerBO();
        customerBO.setName("余磊");
        customerBO.setMobile("17091918167");
        customerBO.setId_no("411524198905111410");
        ylKafkaProducer.produce("yltest",customerBO);
    }
}
