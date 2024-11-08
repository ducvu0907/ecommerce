package com.ducvu.payment_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {
    @Id
    private String id;
    private Integer orderId;
    private Integer userId;
    private String transactionId;
}
