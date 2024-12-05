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
    private Integer userId; // for querying user payments history
    private String transactionId; // references the transaction from the payment gateway
    private String status; // pending (default), completed
    private LocalDateTime payDate;
}
