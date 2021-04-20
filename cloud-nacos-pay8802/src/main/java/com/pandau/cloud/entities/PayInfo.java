package com.pandau.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInfo {
    static   int  a =0;
    private Long id;

    private String serial;

    private Integer sum;

    public static void main(String[] args) {
         ThreadLocal local = new ThreadLocal();
         local.set("hahah");
         new Thread(() ->{
             System.out.println("内部线程 ： "+local.get());
         }).start();
        System.out.println("main:"+local.get());
    }
}

