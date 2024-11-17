package com.ducvu.cart_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
