package com.ducvu.order_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    Integer id;
    Integer userId;
    String description;
    Double totalAmount;
    String status;
    Set<OrderItemResponse> items;
    LocalDateTime createdAt;
}
