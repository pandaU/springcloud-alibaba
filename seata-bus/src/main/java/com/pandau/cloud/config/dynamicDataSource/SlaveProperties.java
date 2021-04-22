package com.pandau.cloud.config.dynamicDataSource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "spring.slave.datasource")
public class SlaveProperties {
    private  String url;

    private  String username;

    private  String password;

    private  String driverClassName;
}
