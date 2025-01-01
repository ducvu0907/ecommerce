package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.request.ProductCreateRequest;
import com.ducvu.product_service.dto.response.CategoryResponse;
import com.ducvu.product_service.dto.response.InventoryResponse;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Inventory;
import com.ducvu.product_service.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class Mapper {
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .sku(product.getSku())
                .title(product.getTitle())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(toCategoryResponse(product.getCategory()))
                .quantity(
                    product.getInventories()
                            .stream()
                            .mapToInt(Inventory::getStock)
                            .sum()
                )
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
    }

    public InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .location(inventory.getLocation())
                .stock(inventory.getStock())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }
}
