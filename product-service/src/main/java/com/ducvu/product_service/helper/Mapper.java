package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.request.CategoryCreateRequest;
import com.ducvu.product_service.dto.request.ProductCreateRequest;
import com.ducvu.product_service.dto.response.CategoryResponse;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class Mapper {
    public Product toProduct(ProductCreateRequest request) {
        return Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .sku(request.getSku())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .sku(product.getSku())
                .title(product.getTitle())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public Category toCategory(CategoryCreateRequest request) {
        return Category.builder()
                .title(request.getTitle())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .products(
                category.getProducts()
                        .stream()
                        .map(this::toProductResponse)
                        .collect(Collectors.toSet())
                )
                .build();
    }


}
