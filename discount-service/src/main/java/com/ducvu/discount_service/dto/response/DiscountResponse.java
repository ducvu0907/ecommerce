package com.ducvu.discount_service.dto.response;

import com.ducvu.discount_service.entity.DiscountType;
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
    DiscountType type;
    Double value;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
