package com.ducvu.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String title;
    private String sku;
    private String imageUrl;
    private String description;
    private Double unitPrice;
    private Integer stockQuantity;
}
