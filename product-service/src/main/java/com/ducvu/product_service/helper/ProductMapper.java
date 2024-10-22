package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.ProductResponse;
import com.ducvu.product_service.entity.Product;

public interface ProductMapper {
    static ProductResponse map(final Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name((product.getName()))
                .sku(product.getSku())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    static Product map(final ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.getId())
                .name((productResponse.getName()))
                .sku(productResponse.getSku())
                .imageUrl(productResponse.getImageUrl())
                .description(productResponse.getDescription())
                .unitPrice(productResponse.getUnitPrice())
                .stockQuantity(productResponse.getStockQuantity())
                .build();
    }

}
