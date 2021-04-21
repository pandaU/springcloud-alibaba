package com.pandau.cloud.cloudkafka18080;

import com.pandau.cloud.util.CommResult;
import com.pandau.cloud.config.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/sendMsg")
    public Object sendMsg(String msg) {
        try {
            this.kafkaTemplate.send(TopicConfig.TOPIC_A, msg);
            log.info("发送消息到第三方kafka成功");
        } catch (Exception e) {
            log.info("发送消息到第三方kafka失败 错误 ------> {}", e.getMessage());
        }
        return CommResult.ok("","SUCCESS");
    }
}
