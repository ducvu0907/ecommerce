package com.ducvu.payment_service.helper;

import com.ducvu.payment_service.dto.PaymentDto;
import com.ducvu.payment_service.entity.Payment;

public class Mapper {
    public static PaymentDto map(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .transactionId(payment.getTransactionId())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
