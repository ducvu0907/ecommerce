package com.ducvu.payment_service.config;

import com.ducvu.payment_service.dto.ApiResponse;
import com.ducvu.payment_service.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order-service", url = "http://localhost:8003")
public interface OrderClient {
    @GetMapping("/{orderId}")
    ApiResponse<OrderResponse> getOrder(@RequestHeader("token") String token, @PathVariable("orderId") String orderId);
}
