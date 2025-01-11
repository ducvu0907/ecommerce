package com.ducvu.payment_service.controller;

import com.ducvu.payment_service.dto.ApiResponse;
import com.ducvu.payment_service.dto.request.CreatePaymentRequest;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.service.PaymentService;
import com.ducvu.payment_service.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final VNPayService vnPayService;

    @GetMapping("/order/{orderId}")
    public ApiResponse<PaymentResponse> getPaymentByOrderId(@RequestHeader("token") String token, @PathVariable("orderId") String orderId, HttpServletRequest httpServletRequest) {
        var res = paymentService.getPaymentByOrderId(token, orderId, httpServletRequest);
        return ApiResponse.<PaymentResponse>builder().result(res).build();
    }

    @PostMapping("/create-payment")
    public ApiResponse<String> createPayment(@RequestHeader("token") String token, @RequestBody CreatePaymentRequest request) {
        var res = paymentService.createPayment(token, request);
        return ApiResponse.<String>builder().result(res).build();
    }

    @GetMapping("/vnpay-return")
    public RedirectView paymentReturn(HttpServletRequest request){
        var res = paymentService.processPayment(request);
        return new RedirectView(res);
    }
}