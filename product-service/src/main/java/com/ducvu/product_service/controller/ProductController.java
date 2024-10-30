package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.ProductRequest;
import com.ducvu.product_service.dto.ProductResponse;
import com.ducvu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String productId) {
        ProductResponse productResponse = productService.getProduct(productId);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.updateProduct(productId, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
