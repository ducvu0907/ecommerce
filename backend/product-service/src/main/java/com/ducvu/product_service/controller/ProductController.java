package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.request.ProductCreateRequest;
import com.ducvu.product_service.dto.request.ProductUpdateRequest;
import com.ducvu.product_service.dto.ApiResponse;
import com.ducvu.product_service.dto.response.ProductResponse;
import com.ducvu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ApiResponse<List<ProductResponse>> getProducts() {
        var res = productService.getProducts();
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }

    @GetMapping("/seller/{sellerId}")
    public ApiResponse<List<ProductResponse>> getProductsBySellerId(@PathVariable("sellerId") String sellerId) {
        var res = productService.getProductsBySellerId(sellerId);
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable("productId") String productId) {
        var res = productService.getProduct(productId);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    @PostMapping("")
    public ApiResponse<ProductResponse> createProduct(@RequestHeader("token") String token, @RequestBody ProductCreateRequest request) {
        var res = productService.createProduct(token, request);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    @PostMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@RequestHeader("token") String token, @PathVariable("productId") String productId, @RequestBody ProductUpdateRequest request) {
        var res = productService.updateProduct(token, productId, request);
        return ApiResponse.<ProductResponse>builder().result(res).build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@RequestHeader("token") String token, @PathVariable("productId") String productId) {
        productService.deleteProduct(token, productId);
        return ApiResponse.<String>builder().result("Product has been deleted").build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String query) {
        var res = productService.searchProducts(query);
        return ApiResponse.<List<ProductResponse>>builder().result(res).build();
    }
}
