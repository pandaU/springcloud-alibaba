package com.pandau.cloud.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

    @KafkaListener(topics = "flink0",containerFactory = "kafkaListenerContainerFactory")
    public void consume2(ConsumerRecord<String, String> consumerRecord, Acknowledgment ack) {
        String content = consumerRecord.value();
        log.info("收到第三方kafka数据,massage={}",content);
        ack.acknowledge();
    }
}
