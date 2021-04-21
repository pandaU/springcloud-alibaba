package com.pandau.cloud.cloudkafka18080.kafka;


import com.pandau.cloud.cloudkafka18080.CloudKafka18080ApplicationTests;
import com.pandau.cloud.config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class producerTest extends CloudKafka18080ApplicationTests {
    /*@Autowired
    private KafkaTemplate<String,String> kafkaOutTemplate;*/
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Test
    public void sendMsg(){
        String msg ="hello kafka";
        try {
                this.kafkaTemplate.send(TopicConfig.TOPIC_A,msg);
                log.info("发送消息到第三方kafka成功");
            } catch (Exception e) {
                log.info("发送消息到第三方kafka失败 错误 ------> {}", e.getMessage());
            }
    }
}
