package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.repository.CategoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> getProducts() {

    }

    public List<ProductDto> getProductsBySellerId(Integer sellerId) {

    }

    public ProductDto getProductById(Integer productId) {

    }

    public ProductDto createProduct(ProductDto productDto) {

    }

    public ProductDto updateProduct(Integer productId, ProductDto productDto) {

    }

    public void deleteProduct(Integer productId) {

    }
}
