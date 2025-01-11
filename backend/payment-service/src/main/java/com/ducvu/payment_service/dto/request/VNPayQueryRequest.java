package com.ducvu.payment_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VNPayQueryRequest {
    String vnp_Command;
    String vnp_CreateDate;
    String vnp_IpAddr;
    String vnp_OrderInfo;
    String vnp_RequestId;
    String vnp_TmnCode;
    String vnp_TransactionDate;
    String vnp_TxnRef;
    String vnp_Version;
    String vnp_SecureHash;
}
