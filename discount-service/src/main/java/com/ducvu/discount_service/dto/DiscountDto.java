package com.ducvu.discount_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountDto {
    private String id;
    private String type;
    private String description;
    private Double value;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer usageLimit;
    private Double minOrder;
    private Integer productId;
    private String promoCode;
    private Boolean isActive;
}
