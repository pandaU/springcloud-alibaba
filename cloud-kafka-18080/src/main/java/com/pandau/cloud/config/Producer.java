/*
package com.pandau.springcloud.cloudkafka18080.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Producer {
    @Value("${kafka.bootstrap-servers}")
    private String outServers;
    @Value("${kafka.producer.acks}")
    private String outAcks;
    //    @Value("${security.protocol}")
//    private String securityProtocol;
//    @Value("${sasl.kerberos.service.name}")
//    private String kerberosServiceName;
//    @Value("${kerberos.domain.name}")
//    private String kerberosDomainName;
//    @Value("${kafka.client.security.mode}")
//    private String isSecurityModel;

    private final Properties props = new Properties();

    private KafkaProducer<String, String> producer;

    public KafkaProducer<String, String> getProducer() {
        props.put("bootstrap.servers", outServers);
        props.put("acks", outAcks);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("security.protocol",securityProtocol);
//        props.put("sasl.kerberos.service.name",kerberosServiceName);
//        props.put("kerberos.domain.name",kerberosDomainName);
        producer = new KafkaProducer<String, String>(props);
        return producer;
    }
}
*/
