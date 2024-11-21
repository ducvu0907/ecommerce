package com.ducvu.discount_service.entity;

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

    private String description;
    private Double amount;
    private Double percent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;
}
