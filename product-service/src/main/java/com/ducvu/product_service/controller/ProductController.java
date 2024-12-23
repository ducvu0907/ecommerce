package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.request.AuthRequest;
import com.ducvu.product_service.dto.request.OrderRequest;
import com.ducvu.product_service.dto.request.ProductCreateRequest;
import com.ducvu.product_service.dto.request.ProductUpdateRequest;
import com.ducvu.product_service.dto.response.ApiResponse;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.repository.ProductRepository;
import com.ducvu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // public
    @GetMapping("")
    public ApiResponse<List<ProductResponse>> getProducts() {
        var res = productService.getProducts();
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }

    // public
    @GetMapping("/seller/{sellerId}")
    public ApiResponse<List<ProductResponse>> getProductsBySeller(@PathVariable("sellerId") Integer sellerId) {
        var res = productService.getProductsBySeller(sellerId);
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }

    // public
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable("productId") Integer productId) {
        var res = productService.getProduct(productId);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    // seller
    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreateRequest request) {
        var res = productService.createProduct(request);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    // seller
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductUpdateRequest request) {
        var res = productService.updateProduct(productId, request);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    // seller
    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@PathVariable("productId") Integer productId, @RequestBody AuthRequest request) {
        productService.deleteProduct(productId, request);
        return ApiResponse.<String>builder().result("Product has been deleted").build();
    }

    // used by order service update the product quantity
    // after placing order or cancelling order
    @PostMapping("/order")
    public ApiResponse<String> order(@RequestBody OrderRequest request) {
        productService.order(request);
        return ApiResponse.<String>builder().result("Products have been updated").build();
    }

    // public
    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String query) {
        var res = productService.search(query);
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }
}
