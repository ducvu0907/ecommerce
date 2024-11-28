package com.ducvu.review_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    Integer id;
    Integer sellerId;
    String sku;
    String title;
    String imageUrl;
    String description;
    Double price;
    Integer quantity;
}
