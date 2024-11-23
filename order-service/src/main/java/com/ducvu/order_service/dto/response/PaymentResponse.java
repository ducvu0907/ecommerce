package com.ducvu.order_service.dto.response;

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
    String status;
    LocalDateTime payDate;
}
