package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.ApiResponse;
import com.ducvu.order_service.dto.response.DiscountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "discount-service", url = "http://localhost:8002")
public interface DiscountClient {
    @GetMapping("/discount/{discountId}")
    ApiResponse<DiscountResponse> getDiscount(@PathVariable String discountId);
}
