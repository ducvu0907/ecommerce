package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.request.CategoryCreateRequest;
import com.ducvu.product_service.dto.response.ApiResponse;
import com.ducvu.product_service.dto.response.CategoryResponse;
import com.ducvu.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    // public
    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getCategories() {
        var res = categoryService.getCategories();
        return ApiResponse.<List<CategoryResponse>>builder().result(res).build();
    }

    // public
    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable("categoryId") Integer categoryId) {
        var res = categoryService.getCategory(categoryId);
        return ApiResponse.<CategoryResponse>builder().result(res).build();
    }

    // admin
    @PostMapping("")
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest request) {
        var res = categoryService.createCategory(request);
        return ApiResponse.<CategoryResponse>builder().result(res).build();
    }
}
