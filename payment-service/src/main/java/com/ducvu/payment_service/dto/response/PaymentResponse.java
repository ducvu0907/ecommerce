package com.ducvu.payment_service.dto.response;

import com.ducvu.payment_service.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentResponse {
    String id;
    Integer orderId;
    Integer userId;
    String transactionId;
    PaymentStatus status;
    LocalDateTime payDate;
}
