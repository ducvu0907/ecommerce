package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.request.AuthRequest;
import com.ducvu.order_service.dto.response.ApiResponse;
import com.ducvu.order_service.dto.response.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cart-service", url = "http://localhost:8001")
public interface CartClient {
    @DeleteMapping(value = "/carts/items", consumes = "application/json")
    ApiResponse<String> deleteItems(AuthRequest request);

    @PostMapping(value = "/carts/cart", consumes = "application/json")
    ApiResponse<CartResponse> getMyCart(AuthRequest request);
}
