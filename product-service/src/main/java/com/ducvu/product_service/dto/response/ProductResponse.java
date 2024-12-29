package com.ducvu.product_service.dto.response;

import com.ducvu.product_service.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    String id;
    String sellerId;
    String sku;
    String title;
    String imageUrl;
    String description;
    Double price;
    CategoryResponse category;
    Integer quantity;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
