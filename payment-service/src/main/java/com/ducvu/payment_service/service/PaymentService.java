package com.ducvu.payment_service.service;

import com.ducvu.payment_service.dto.PaymentDto;
import com.ducvu.payment_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: integrate with a payment gateway
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentDto createPayment() {

    }

    public PaymentDto getPaymentById(String paymentId) {

    }

    public List<PaymentDto> getPaymentsByUserId(String userId) {

    }
}
