package com.ducvu.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private Integer sellerId;
    private String title;
    private String imageUrl;
    private String sku;
    private Double price;
    private Integer quantity;
}
