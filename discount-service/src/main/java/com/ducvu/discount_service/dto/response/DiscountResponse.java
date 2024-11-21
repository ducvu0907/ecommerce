package com.ducvu.discount_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    String id;
    String description;
    Double amount;
    Double percent;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Boolean isActive;
}
