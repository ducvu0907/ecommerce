package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // public
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts() {

    }

    // public
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductDto>> getProductsOfSeller(@PathVariable("sellerId") String sellerId) {

    }

    // public
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") String productId) {

    }

    // seller
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

    }

    // seller
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDto productDto) {

    }

    // seller
    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") String productId) {

    }

}
