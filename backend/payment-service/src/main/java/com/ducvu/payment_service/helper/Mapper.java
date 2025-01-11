package com.ducvu.payment_service.helper;

import com.ducvu.payment_service.dto.response.PaymentResponse;
import com.ducvu.payment_service.dto.response.VNPayResponse;
import com.ducvu.payment_service.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Mapper {
    public PaymentResponse toPaymentResponse(VNPayResponse vnPayResponse) {
        // reformat for client compatibility
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime payDate = LocalDateTime.parse(vnPayResponse.getVnp_PayDate(), formatter);
        int formattedAmount = Integer.parseInt(vnPayResponse.getVnp_Amount()) / 100;

        return PaymentResponse.builder()
                .amount(formattedAmount)
                .payDate(payDate)
                .orderId(vnPayResponse.getVnp_OrderInfo())
                .bankCode(vnPayResponse.getVnp_BankCode())
                .build();
    }
}
