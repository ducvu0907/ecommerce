package com.ducvu.payment_service.service;

import com.ducvu.payment_service.config.OrderClient;
import com.ducvu.payment_service.config.UserClient;
import com.ducvu.payment_service.dto.request.CreatePaymentRequest;
import com.ducvu.payment_service.dto.response.AuthResponse;
import com.ducvu.payment_service.dto.response.OrderResponse;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.entity.Payment;
import com.ducvu.payment_service.helper.Mapper;
import com.ducvu.payment_service.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    private final VNPayService vnPayService;

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

    public String createPayment(String token, CreatePaymentRequest request) {
        log.info("Payment service; Create VNPay payment");

        var authResponse = validateToken(token);

        String orderId = request.getOrderId();

        // FIXME: comment out to test
        // var orderResponse = getOrder(token, orderId);
        //if (!orderResponse.getBuyerId().equals(authResponse.getUserId())) {
        //    throw new RuntimeException("Unauthorized");
        //}

        paymentRepository.findByOrderId(orderId)
                .ifPresent(payment -> {
                    throw new RuntimeException("Payment already exists");
                });

        return vnPayService.createPaymentUrl(10000, orderId);
    }

    public String processPayment(HttpServletRequest request) {
        log.info("Payment service; Process VNPay payment");

        int paymentResult = vnPayService.getPaymentResult(request);

        if (paymentResult == 0) {
            throw new RuntimeException("Payment failed");
        }

        String transactionId = request.getParameter("vnp_TransactionNo");
        String orderId = request.getParameter("vnp_OrderInfo");

        Payment payment = Payment.builder()
                .orderId(orderId)
                .transactionId(transactionId)
                .build();

        paymentRepository.save(payment);

        return "http://localhost:3000/payments/" + orderId;
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
