package com.pandau.cloud.service.impl;

import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.web.cors.CorsConfiguration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MySvc {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String say(){
       return "hello proxy";
    }
    public void init(){
        MySvc mySvc = new MySvc();
        System.out.println("init");
    }
    public static int getChildStrCounts(String parentStr, String childStr) {
        if (childStr ==null || childStr ==""){
            return 0;
        }
        int propsIndex = 0;
        int counts = 0; // properties 出现几次代表字段嵌套几层
        int index;
        while ((index = parentStr.indexOf(childStr, propsIndex)) != -1) {
                counts++;
            propsIndex = parentStr.indexOf(childStr, propsIndex) + childStr.length();
        }
        return counts;
    }

    public static void main(String[] args) {
        String a = "hellodsdsdhello";
        String b = "";
        int counts = getChildStrCounts(a, b);
        System.out.println(counts);
    }
}
