/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yl.core.kafka;

import com.yl.core.bean.CustomerBO;
import com.zhixindu.commons.utils.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/4/28
 * @description
 */
public class YlKafkaConsumer implements Serializable{

    private static final long serialVersionUID = -2516563458415433901L;
    private static final Logger LOG = LoggerFactory.getLogger(YlKafkaConsumer.class);


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
//        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "1000");
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
//        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "range");
//      properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY, "roundrobin");
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String,String>(properties);
        Collection<String> topics = new ArrayList<>();
        topics.add("test");
        topics.add("yltest");
        kafkaConsumer.subscribe(topics);
//      kafkaConsumer.subscribe("*");
        boolean isRunning = true;
//        while(isRunning) {

            try {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                if (!records.isEmpty()) {
                    LOG.info("Got {} messages", records.count());
                    for (ConsumerRecord<String, String> record : records) {
                        LOG.info("Message with partition: {}, offset: {}, key: {}, value: {}",
                                record.partition(), record.offset(), record.key(), JsonUtil.toJsonString(record.value()));
                        CustomerBO customerBO = JsonUtil.parseBean(record.value(),CustomerBO.class);

                    }
                } else {
                    LOG.info("No messages to consume");
                }
            } catch (SerializationException e) {
                LOG.warn("Failed polling some records", e);
            }
//        }
        kafkaConsumer.close();

    }



}
