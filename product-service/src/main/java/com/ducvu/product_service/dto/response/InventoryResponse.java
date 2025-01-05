package com.ducvu.product_service.dto.response;

import com.ducvu.product_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    String id;
    String location;
    String productId;
    Integer stock;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
