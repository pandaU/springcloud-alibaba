package com.pandau.cloud.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskDemo {
    @Scheduled(cron = "1 * * * * *")
    public void say(){
        System.out.println("haha");
    }
}
