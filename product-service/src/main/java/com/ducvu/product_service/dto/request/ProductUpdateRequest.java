package com.ducvu.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {
    String token;
    String title;
    String description;
    String sku;
    String imageUrl;
    Double price;
    Integer quantity;
}
