package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.ProductRequest;
import com.ducvu.product_service.dto.ProductResponse;
import com.ducvu.product_service.entity.Product;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    public ProductResponse map(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name((product.getName()))
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public boolean validCreateProductRequest(ProductRequest request) {
        return request.getName() != null
                && request.getImageUrl() != null
                && request.getUnitPrice() != null
                && request.getStockQuantity() != null;
    }
}
