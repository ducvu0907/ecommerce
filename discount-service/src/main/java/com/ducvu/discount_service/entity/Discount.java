package com.ducvu.discount_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(value = "discounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Discount {
    @Id
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscountType type;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
