package com.ducvu.payment_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Payment { // TODO: refactor this for vnpay gateway
    @Id
    private String id;

    private Integer orderId;

    @Indexed
    private Integer userId; // for querying user payments history

    @Indexed
    private String transactionId; // references the transaction from the payment gateway

    private PaymentStatus status;
    private LocalDateTime payDate;
}
