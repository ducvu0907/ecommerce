package com.ducvu.payment_service.service;

import com.ducvu.payment_service.config.UserClient;
import com.ducvu.payment_service.dto.request.PayOrderRequest;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.entity.Payment;
import com.ducvu.payment_service.helper.Mapper;
import com.ducvu.payment_service.repository.PaymentRepository;
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

    // FIXME: use order client to authenticate user
    public PaymentResponse getPaymentByOrderId(String token, String orderId) {
        log.info("Payment service; Get payment by order id");

        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapper.toPaymentResponse(payment);
    }

    // TODO: integrate with vnpay
    public PaymentResponse payOrder(String token, PayOrderRequest request) {
        return new PaymentResponse();
    }

}
