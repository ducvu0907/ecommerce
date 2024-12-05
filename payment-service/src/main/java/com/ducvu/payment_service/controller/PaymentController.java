package com.ducvu.payment_service.controller;

import com.ducvu.payment_service.dto.request.AuthRequest;
import com.ducvu.payment_service.dto.request.CreatePaymentRequest;
import com.ducvu.payment_service.dto.request.PayOrderRequest;
import com.ducvu.payment_service.dto.response.ApiResponse;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("")
    public ApiResponse<List<PaymentResponse>> getMyPayments(@RequestBody AuthRequest request) {
        var res = paymentService.getMyPayments(request);
        return ApiResponse.<List<PaymentResponse>>builder().result(res).build();
    }

    @PostMapping("/order/{orderId}")
    public ApiResponse<PaymentResponse> getPayment(@PathVariable Integer orderId, @RequestBody AuthRequest request) {
        var res = paymentService.getPaymentByOrderId(orderId, request);
        return ApiResponse.<PaymentResponse>builder().result(res).build();
    }

    @PostMapping("/create")
    public ApiResponse<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        var res = paymentService.createPayment(request);
        return ApiResponse.<PaymentResponse>builder().result(res).build();
    }

}