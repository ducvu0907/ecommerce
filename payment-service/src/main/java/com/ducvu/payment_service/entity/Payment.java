package com.ducvu.payment_service.entity;

import jakarta.persistence.PrePersist;
import lombok.*;
import org.hibernate.annotations.Index;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(value = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Payment {
    @Id
    private String id;

    @Indexed
    private String orderId;

    @Indexed
    private String transactionId;
}
