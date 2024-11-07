package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;

import java.util.stream.Collectors;

public class Mapper {

    public static CategoryDto map(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .productDtos(
                        category.getProducts()
                                .stream()
                                .map(Mapper::map)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static Category map(CategoryDto categoryDto) {
        return Category.builder()
                .title(categoryDto.getTitle())
                .build();
    }

    public static Product map(ProductDto productDto) {
        return Product.builder()
                .sellerId(productDto.getSellerId())
                .title(productDto.getTitle())
                .imageUrl(productDto.getImageUrl())
                .sku(productDto.getSku())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

    public static ProductDto map(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .title(product.getTitle())
                .imageUrl(product.getImageUrl())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
