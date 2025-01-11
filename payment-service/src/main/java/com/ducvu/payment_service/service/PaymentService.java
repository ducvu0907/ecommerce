package com.ducvu.payment_service.service;

import com.ducvu.payment_service.config.OrderClient;
import com.ducvu.payment_service.config.UserClient;
import com.ducvu.payment_service.config.VNPayClient;
import com.ducvu.payment_service.dto.request.CreatePaymentRequest;
import com.ducvu.payment_service.dto.response.AuthResponse;
import com.ducvu.payment_service.dto.response.OrderResponse;
import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.dto.response.VNPayResponse;
import com.ducvu.payment_service.entity.Payment;
import com.ducvu.payment_service.helper.Mapper;
import com.ducvu.payment_service.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final VNPayClient vnPayClient;

    @Value("${client.url}/payment-failed?orderId=")
    private String paymentFailedUrl;

    @Value("${client.url}/payment-succeeded?orderId=")
    private String paymentSucceededUrl;

    public PaymentResponse getPaymentByOrderId(String token, String orderId) {
        log.info("Payment service; Get payment by order id");

        var authResponse = validateToken(token);

        var orderResponse = getOrder(token, orderId);
        if (!orderResponse.getBuyerId().equals(authResponse.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        var vnpayTransaction = getVNPayTransaction(payment.getTransactionRef(), payment.getOrderId());
        return mapper.toPaymentResponse(vnpayTransaction);
    }

    @Transactional
    public String createPayment(String token, CreatePaymentRequest request) {
        log.info("Payment service; Create VNPay payment");

        var authResponse = validateToken(token);

        String orderId = request.getOrderId();

        var orderResponse = getOrder(token, orderId);
        if (!orderResponse.getBuyerId().equals(authResponse.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        if (!orderResponse.getStatus().equals("PENDING")) {
            throw new RuntimeException("Order is not valid to be paid");
        }

        paymentRepository.findByOrderId(orderId)
                .ifPresent(payment -> {
                    throw new RuntimeException("Payment already exists");
                });

        return vnPayService.createPaymentUrl(10000, orderId);
    }

    @Transactional
    public String processPayment(HttpServletRequest request) {
        log.info("Payment service; Process VNPay payment");

        int paymentResult = vnPayService.getPaymentResult(request);
        String transactionRef = request.getParameter("vnp_TxnRef");
        String orderId = request.getParameter("vnp_OrderInfo");

        if (paymentResult == 0) {
            return paymentFailedUrl + orderId;
        }

        Payment payment = Payment.builder()
                .orderId(orderId)
                .transactionRef(transactionRef)
                .build();

        paymentRepository.save(payment);
        orderClient.payOrder(orderId);

        return paymentSucceededUrl + orderId;
    }

    private VNPayResponse getVNPayTransaction(String txnRef, String orderInfo) {
        var req = vnPayService.queryTransaction(txnRef, orderInfo);
        log.info("Send vnpay query request: {}", req);

        var vnpayRes = vnPayClient.getTransaction(req);

        log.info("Get vnpay transaction: {}", vnpayRes);
        if (!"00".equals(vnpayRes.getVnp_ResponseCode())) {
            throw new RuntimeException("Transaction invalid");
        }

        return vnpayRes;
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
