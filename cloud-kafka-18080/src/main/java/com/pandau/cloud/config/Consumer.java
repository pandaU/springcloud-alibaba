/*
package com.pandau.springcloud.cloudkafka18080.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class Consumer {
    @Value("${kafka.bootstrap-servers}")
    private String outServers;

    @Value("${kafka.group}")
    private String group;

    @Value("${kafka.enable.auto.commit}")
    private String auto;

    @Value("${kafka.auto.time}")
    private String autoTime;

    @Value("${kafka.timeout}")
    private String timeout;

    @Value("${kafka.key.deserializer}")
    private String keyDeserializer;

    @Value("${kafka.value.deserializer}")
    private String valueDeserializer;
    private final Properties props = new Properties();
    private KafkaConsumer<String, String> consumer;

    public KafkaConsumer<String, String>  getConsumer(){
        // Broker连接地址
        props.put("bootstrap.servers", outServers);
        // Group id
        props.put("group.id", group);
        // 是否自动提交offset
        props.put("enable.auto.commit", auto);
        // 自动提交offset的时间间隔
        props.put("auto.commit.interval.ms", autoTime);
        // 会话超时时间
        props.put("session.timeout.ms", timeout);
        // 消息Key值使用的反序列化类
        props.put("key.deserializer", keyDeserializer);
        // 消息内容使用的反序列化类
        props.put("value.deserializer",valueDeserializer);
        consumer = new KafkaConsumer<String, String>(props);
        return consumer;
    }
}
*/
