package com.ducvu.payment_service.helper;

import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .transactionId(payment.getTransactionId())
                .status(payment.getStatus())
                .payDate(payment.getPayDate())
                .build();
    }
}
