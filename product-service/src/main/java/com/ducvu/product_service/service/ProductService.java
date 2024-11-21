package com.ducvu.product_service.service;

import com.ducvu.product_service.config.UserClient;
import com.ducvu.product_service.dto.request.AuthRequest;
import com.ducvu.product_service.dto.request.ProductCreateRequest;
import com.ducvu.product_service.dto.request.ProductUpdateRequest;
import com.ducvu.product_service.dto.response.AuthResponse;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;
import com.ducvu.product_service.helper.Mapper;
import com.ducvu.product_service.repository.CategoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.ec.ScaleXPointMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;
    private final UserClient userClient;

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public List<ProductResponse> getProductsBySeller(Integer sellerId) {
        return productRepository.findBySellerId(sellerId)
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapper.toProductResponse(product);
    }

    public ProductResponse createProduct(ProductCreateRequest request) {
        productRepository.findBySku(request.getSku())
                        .ifPresent(product -> {throw new RuntimeException("Product already exists");});

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // check user role
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getRole().equals("seller")) {
            throw new RuntimeException("Unauthorized");
        }

        Product product = mapper.toProduct(request);
        product.setSellerId(authResponse.getResult().getUserId());
        product.setCategory(category);
        productRepository.save(product);
        category.getProducts().add(product);
        categoryRepository.save(category);
        return mapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(Integer productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product already exists"));

        // check user role
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getRole().equals("seller")) {
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

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        productRepository.save(product);
        return mapper.toProductResponse(product);
    }

    public void deleteProduct(Integer productId, AuthRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = product.getCategory();

        // check user role
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        if (!authResponse.getResult().getUserId().equals(product.getSellerId()) || !authResponse.getResult().getRole().equals("seller")) {
            throw new RuntimeException("Unauthorized");
        }

        productRepository.deleteById(productId);
        category.getProducts().remove(product);
        categoryRepository.save(category);
    }

}
