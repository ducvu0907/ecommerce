package com.ducvu.order_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    String id;
    String userId;
    List<CartItemResponse> items;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
