package com.ducvu.payment_service.config;

import com.ducvu.payment_service.dto.request.VNPayQueryRequest;
import com.ducvu.payment_service.dto.response.VNPayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "vnpay-gateway", url = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction")
public interface VNPayClient {
    @PostMapping
    VNPayResponse getTransaction(@RequestBody VNPayQueryRequest request);
}
