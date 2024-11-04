package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.repository.DtoCollectionResponse;
import com.ducvu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<ProductDto>> getProducts() {
        log.info("Product controller, fetch all products");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.productService.getProducts()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") String productId) {
        log.info("Product controller, fetch product by id");
        return ResponseEntity.ok(this.productService.getProductById(Integer.valueOf(productId)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Product controller, create product");
        return ResponseEntity.ok(this.productService.createProduct(productDto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDto productDto) {
        log.info("Product controller, update product");
        return ResponseEntity.ok(this.productService.updateProduct(Integer.valueOf(productId), productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("productId") final String productId) {
        log.info("Product controller, delete product");
        this.productService.deleteProduct(Integer.valueOf(productId));
        return ResponseEntity.ok(true);
    }
}
