package com.pandau.cloud.service;

public interface BusinessService {
    void purchase(String userId, String commodityCode, int orderCount);
}
