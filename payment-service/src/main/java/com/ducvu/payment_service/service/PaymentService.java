package com.ducvu.payment_service.service;

import com.ducvu.payment_service.config.OrderClient;
import com.ducvu.payment_service.config.UserClient;
import com.ducvu.payment_service.dto.request.PayOrderRequest;
import com.ducvu.payment_service.dto.response.AuthResponse;
import com.ducvu.payment_service.dto.response.OrderResponse;
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
    private final OrderClient orderClient;
    private final Mapper mapper;

    public PaymentResponse getPaymentByOrderId(String token, String orderId) {
        log.info("Payment service; Get payment by order id");

        var authResponse = validateToken(token);
        var orderResponse = getOrder(token, orderId);

        if (!orderResponse.getBuyerId().equals(authResponse.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapper.toPaymentResponse(payment);
    }

    // TODO: integrate with vnpay
    public PaymentResponse createPayment(String token, PayOrderRequest request) {
        return new PaymentResponse();
    }

    private OrderResponse getOrder(String token, String orderId) {
        var orderResponse = orderClient.getOrder(token, orderId);
        if (orderResponse.getResult() == null) {
            throw new RuntimeException("Order not found");
        }
        return orderResponse.getResult();
    }

    private AuthResponse validateToken(String token) {
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }
        return authResponse.getResult();
    }
}
