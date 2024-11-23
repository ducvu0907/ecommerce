package com.ducvu.payment_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Payment {
    @Id
    private String id;

    private Integer orderId;
    private Integer userId;
    private String transactionId; // id from the payment gateway
    private String status; // pending, completed
    private LocalDateTime payDate;
}
