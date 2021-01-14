package com.pandau.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public static String myBlockHandler1(BlockException excption) {
        return "test 出错啦";
    }
    public static String myBlockHandler2(BlockException excption) {
        return "出错啦";
    }
}
