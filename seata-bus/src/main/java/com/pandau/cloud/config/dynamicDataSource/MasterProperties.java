package com.pandau.cloud.config.dynamicDataSource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
//@ConfigurationProperties(prefix = "spring.master.datasource")
public class MasterProperties {
    private  String url;

    private  String username;

    private  String password;

    private  String driverClassName;

   /* private  int initialSize;

    private  int minIdle;

    private  int maxActive;

    private  int maxWait;*/
}
