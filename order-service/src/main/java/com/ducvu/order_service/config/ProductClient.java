package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.request.OrderProductsRequest;
import com.ducvu.order_service.dto.response.ApiResponse;
import com.ducvu.order_service.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "http://localhost:8005")
public interface ProductClient {
    @GetMapping("/products/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable("productId") Integer productId);
    @PostMapping("/products/order")
    ApiResponse<String> order(@RequestBody OrderProductsRequest request);
}
