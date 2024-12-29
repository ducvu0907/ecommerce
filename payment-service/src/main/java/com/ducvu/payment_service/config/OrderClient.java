package com.ducvu.payment_service.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service", url = "http://localhost:8003")
public interface OrderClient {

}
