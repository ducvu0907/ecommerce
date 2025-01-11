package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.ApiResponse;
import com.ducvu.order_service.dto.request.ShipmentRequest;
import com.ducvu.order_service.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductClient {
    @GetMapping("/products/{productId}")
    ApiResponse<ProductResponse> getProduct(@PathVariable("productId") String productId);

    @PostMapping("/shipments/plan")
    ApiResponse<String> planOrderShipment(@RequestBody ShipmentRequest request);

    @DeleteMapping("/shipments/{orderId}/cancel")
    ApiResponse<String> cancelOrderShipment(@PathVariable("orderId") String orderId);
}
