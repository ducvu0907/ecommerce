package com.ducvu.payment_service.dto.response;

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
    int amount;
    LocalDateTime payDate;
    String orderId;
    String bankCode;
}
