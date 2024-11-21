package com.ducvu.discount_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiscountRequest {
    String token;
    String description;
    Double amount;
    Double percent;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Boolean isActive;
}
