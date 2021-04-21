package com.pandau.cloud.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Configuration
@EnableKafka
public class KafkaConfig {

    /*------INTER_KAFKA配置------*/
    @Value("${spring.kafka.bootstrap-servers}")
    private String innerServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String innerGroupid;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String innerEnableAutoCommit;
    @Value("${spring.kafka.listener.concurrency}")
    private String innerConcurrency;
    @Value("${spring.kafka.producer.acks}")
    private String innerAcks;
    @Value("${spring.kafka.properties.max.poll.interval.ms}")
    private String innePollTimeout;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private String innerPollRecogs;
    @Value("${spring.kafka.listener.ack-mode}")
    private String innerAckModel;

    /*------OUTER_KAFKA配置------*/
    @Value("${spring.outkafka.bootstrap-servers}")
    private String outServers;
    @Value("${spring.outkafka.consumer.group-id}")
    private String outGroupid;
    @Value("${spring.outkafka.consumer.enable-auto-commit}")
    private String outEnableAutoCommit;
    @Value("${spring.outkafka.listener.concurrency}")
    private String outConcurrency;
    @Value("${spring.outkafka.producer.acks}")
    private String outAcks;
    @Value("${spring.outkafka.properties.max.poll.interval.ms}")
    private String outPollTimeout;
    @Value("${spring.outkafka.consumer.max-poll-records}")
    private String outPollRecogs;
    @Value("${spring.outkafka.listener.ack-mode}")
    private String outAckModel;
    /*------INTER_KAFKA配置开始------*/
    @Bean
    @Primary /*默认的kafka配置*/
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(Integer.getInteger(innerConcurrency));
        factory.getContainerProperties().setPollTimeout(Long.parseLong(innePollTimeout));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.valueOf(innerAckModel.toUpperCase()));
        return factory;
    }
    @Bean//第一个消费者工厂的bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, innerServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, innerGroupid);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, innerEnableAutoCommit);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,innerPollRecogs);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
    //inner生产者配置
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }
    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, innerServers);
        props.put(ProducerConfig.ACKS_CONFIG, innerAcks);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    /*------INTER_KAFKA配置结束------*/


    /*------OUT_KAFKA配置开始------*/

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactoryOutSchedule() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryOutSchedule());
        factory.setConcurrency(Integer.getInteger(outConcurrency));
        factory.getContainerProperties().setPollTimeout(Long.parseLong(outPollTimeout));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.valueOf(outAckModel.toUpperCase()));
        return factory;
    }
    @Bean
    public ConsumerFactory<Integer, String> consumerFactoryOutSchedule() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigsOutSchedule());
    }
    @Bean
    public Map<String, Object> consumerConfigsOutSchedule() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, outServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, outGroupid);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, outEnableAutoCommit);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,outPollRecogs);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
    @Bean //生产者工厂配置
    public ProducerFactory<String, String> producerOutFactory() {
        return new DefaultKafkaProducerFactory<>(senderOutProps());
    }
    @Bean //kafka发送消息模板
    public KafkaTemplate<String, String> kafkaOutTemplate() {
        return new KafkaTemplate<String, String>(producerOutFactory());
    }
    private Map<String, Object> senderOutProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, outServers);
        props.put(ProducerConfig.ACKS_CONFIG, outAcks);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    /*------OUT_KAFKA配置结束-----*/
}
