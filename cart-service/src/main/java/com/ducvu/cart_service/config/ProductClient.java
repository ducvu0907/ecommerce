package com.ducvu.cart_service.config;

import com.ducvu.cart_service.dto.ApiResponse;
import com.ducvu.cart_service.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8005")
public interface ProductClient {
    @GetMapping("/products/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable("productId") String productId);
}
