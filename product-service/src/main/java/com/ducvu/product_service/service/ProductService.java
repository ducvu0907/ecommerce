package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.ProductRequest;
import com.ducvu.product_service.dto.ProductResponse;
import com.ducvu.product_service.entity.Product;
import com.ducvu.product_service.exception.ProductException;
import com.ducvu.product_service.helper.Util;
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
    private final Util util;

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(util::map).toList();
    }

    public ProductResponse getProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product not found"));

        return util.map(product);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        if (!util.validCreateProductRequest(productRequest)) {
            throw new ProductException("Product needs name, image, price and quantity");
        }

        Product product = Product.builder()
                .name(productRequest.getName())
                .imageUrl(productRequest.getImageUrl())
                .description(productRequest.getDescription())
                .unitPrice(productRequest.getUnitPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());

        return util.map(product);
    }


    public ProductResponse updateProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product not found"));

        if (productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }

        if (productRequest.getImageUrl() != null) {
            product.setImageUrl(productRequest.getImageUrl());
        }

        if (productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }

        if (productRequest.getUnitPrice() != null) {
            product.setUnitPrice(productRequest.getUnitPrice());
        }

        if (productRequest.getStockQuantity() != null) {
            product.setStockQuantity(productRequest.getStockQuantity());
        }

        productRepository.save(product);
        log.info("Product {} is updated", product.getId());

        return util.map(product);
    }

    public void deleteProduct(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductException("Product not found");
        }

        productRepository.deleteById(productId);
        log.info("Product {} is deleted", productId);
    }
}
