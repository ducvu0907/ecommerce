package com.ducvu.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private String id;
    private Integer orderId;
    private Integer userId;
    private String transactionId;
    private LocalDateTime createdAt;
}
