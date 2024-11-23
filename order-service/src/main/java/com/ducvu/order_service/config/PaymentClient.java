package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.request.CreatePaymentRequest;
import com.ducvu.order_service.dto.response.ApiResponse;
import com.ducvu.order_service.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", url = "http://localhost:8004")
public interface PaymentClient {
    @PostMapping(value = "/payments/create", consumes = "application/json")
    ApiResponse<PaymentResponse> createPayment(CreatePaymentRequest request);
}
