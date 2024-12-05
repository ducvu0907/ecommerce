package com.ducvu.payment_service.service;

import com.ducvu.payment_service.config.UserClient;
import com.ducvu.payment_service.dto.request.AuthRequest;
import com.ducvu.payment_service.dto.request.CreatePaymentRequest;
import com.ducvu.payment_service.dto.request.PayOrderRequest;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.entity.Payment;
import com.ducvu.payment_service.helper.Mapper;
import com.ducvu.payment_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserClient userClient;
    private final Mapper mapper;

    public List<PaymentResponse> getMyPayments(AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token not found");
        }

        return paymentRepository.findByUserId(authResponse.getResult().getUserId())
                .stream()
                .map(mapper::toPaymentResponse)
                .toList();
    }

    public PaymentResponse getPaymentByOrderId(Integer orderId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token not found");
        }

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (!payment.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        return mapper.toPaymentResponse(payment);
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token not found");
        }

        Payment payment = Payment.builder()
                .userId(authResponse.getResult().getUserId())
                .orderId(request.getOrderId())
                .status("pending")
                .build();

        paymentRepository.save(payment);
        return mapper.toPaymentResponse(payment);
    }

    // TODO: implement pay order using vnpay/zalopay
}
