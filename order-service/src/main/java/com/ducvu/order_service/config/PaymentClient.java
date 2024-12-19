package com.ducvu.order_service.config;

import com.ducvu.order_service.dto.request.AuthRequest;
import com.ducvu.order_service.dto.request.CreatePaymentRequest;
import com.ducvu.order_service.dto.response.ApiResponse;
import com.ducvu.order_service.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8004")
public interface PaymentClient {
    @PostMapping(value = "/payments/create", consumes = "application/json")
    ApiResponse<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request);

    @DeleteMapping(value = "/payments/order/{orderId}", consumes = "application/json")
    ApiResponse<String> deletePayment(@PathVariable Integer orderId, @RequestBody AuthRequest request);
}
