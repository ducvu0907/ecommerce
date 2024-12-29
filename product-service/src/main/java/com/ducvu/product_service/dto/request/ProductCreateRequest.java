package com.ducvu.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateRequest {
    String title;
    String description;
    String imageUrl;
    String sku;
    Double price;
    String categoryId;
}
