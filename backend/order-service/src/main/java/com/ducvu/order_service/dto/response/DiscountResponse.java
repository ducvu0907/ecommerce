package com.ducvu.order_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    String id;
    String type;
    Double value;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
