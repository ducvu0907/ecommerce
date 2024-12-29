package com.ducvu.product_service.service;

import com.ducvu.product_service.config.UserClient;
import com.ducvu.product_service.dto.request.ProductUpdateRequest;
import com.ducvu.product_service.dto.request.*;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;
import com.ducvu.product_service.helper.Mapper;
import com.ducvu.product_service.repository.CategoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;
    private final UserClient userClient;

    public List<ProductResponse> getProducts() {
        log.info("Product service; Get products");

        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public List<ProductResponse> getProductsBySellerId(String sellerId) {
        log.info("Product service; Get products by seller id");

        return productRepository.findBySellerId(sellerId)
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProduct(String productId) {
        log.info("Product service; Get product");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapper.toProductResponse(product);
    }

    public ProductResponse createProduct(String token, ProductCreateRequest request) {
        log.info("Product service; Create product");

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category invalid"));

        // check user role
        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        Product product = Product.builder()
                .sellerId(authResponse.getResult().getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .sku(request.getSku())
                .price(request.getPrice())
                .category(category)
                .build();

        productRepository.save(product);
        return mapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(String token, String productId, ProductUpdateRequest request) {
        log.info("Product service; Update product");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product already exists"));

        // check user role
        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(product.getSellerId()) || !authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        if (request.getTitle() != null) {
            product.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getImageUrl() != null) {
            product.setImageUrl(request.getImageUrl());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getSku() != null) {
            product.setSku(request.getSku());
        }

        productRepository.save(product);
        return mapper.toProductResponse(product);
    }

    public void deleteProduct(String token, String productId) {
        log.info("Product service; Delete product");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = product.getCategory();

        // check user role
        var authResponse = userClient.authenticate(token);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(product.getSellerId()) || !authResponse.getResult().getRole().equals("SELLER")) {
            throw new RuntimeException("Unauthorized");
        }

        productRepository.deleteById(productId);
    }

    public List<ProductResponse> searchProducts(String query) {
        log.info("Product service; Search products");

        List<Product> products = productRepository.searchProducts(query);

        return products.stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    // TODO
    public void order(OrderRequest request) {
    }

}
