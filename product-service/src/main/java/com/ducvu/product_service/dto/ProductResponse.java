package com.ducvu.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String sku;
    private String imageUrl;
    private String description;
    private Double unitPrice;
    private Integer stockQuantity;
}
