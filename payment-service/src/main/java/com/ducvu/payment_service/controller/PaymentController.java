package com.ducvu.payment_service.controller;

import com.ducvu.payment_service.dto.PaymentDto;
import com.ducvu.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    // user
    @PostMapping("")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {

    }

    // user
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable String paymentId) {

    }

    // user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByUserId(@PathVariable String userId) {

    }

}
