package com.ducvu.product_service.helper;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;

public class Mapper {

    public static CategoryDto map(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryTitle(category.getCategoryTitle())
                .build();
    }

    public static Category map(CategoryDto categoryDto) {
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryTitle(categoryDto.getCategoryTitle())
                .build();
    }

    public static Product map(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .sellerId(productDto.getSellerId())
                .productTitle(productDto.getProductTitle())
                .imageUrl(productDto.getImageUrl())
                .sku(productDto.getSku())
                .priceUnit(productDto.getPriceUnit())
                .quantity(productDto.getQuantity())
                .category(map(productDto.getCategoryDto()))
                .build();
    }

    public static ProductDto map(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .sellerId(product.getSellerId())
                .productTitle(product.getProductTitle())
                .imageUrl(product.getImageUrl())
                .sku(product.getSku())
                .priceUnit(product.getPriceUnit())
                .quantity(product.getQuantity())
                .categoryDto(map(product.getCategory()))
                .build();
    }

}
