package com.ducvu.order_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    String id;
    Integer sellerId;
    String sku;
    String title;
    String imageUrl;
    String description;
    Double price;
    Integer quantity;
}
