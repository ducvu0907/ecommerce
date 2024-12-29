package com.ducvu.payment_service.controller;

import com.ducvu.payment_service.dto.ApiResponse;
import com.ducvu.payment_service.dto.request.PayOrderRequest;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/order/{orderId}")
    public ApiResponse<PaymentResponse> getPaymentByOrderId(@RequestHeader("token") String token, @PathVariable("orderId") String orderId) {
        var res = paymentService.getPaymentByOrderId(token, orderId);
        return ApiResponse.<PaymentResponse>builder().result(res).build();
    }

    @PostMapping("/pay")
    public ApiResponse<PaymentResponse> payOrder(@RequestHeader("token") String token, PayOrderRequest request) {
        var res = paymentService.payOrder(token, request);
        return ApiResponse.<PaymentResponse>builder().result(res).build();
    }
}