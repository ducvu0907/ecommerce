package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.ApiResponse;
import com.ducvu.order_service.dto.response.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cart-service", url = "http://localhost:8001")
public interface CartClient {
    @GetMapping("/carts/me")
    ApiResponse<CartResponse> getMyCart(@RequestHeader("token") String token);

    @DeleteMapping("/carts/me/items")
    ApiResponse<String> emptyCart(@RequestHeader("token") String token);
}
