package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.ProductRequest;
import com.ducvu.product_service.dto.ProductResponse;
import com.ducvu.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getProducts() {
        log.info("Fetching all products...");
    }

    public ProductResponse getProductById(String productId) {

    }

    public ProductResponse createProduct(ProductRequest productRequest) {

    }


    public ProductResponse updateProduct(ProductRequest productRequest) {

    }

    public void deleteProductById(String productId) {

    }
}
