package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.ApiResponse;
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

    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getCategories() {
        var res = categoryService.getCategories();
        return ApiResponse.<List<CategoryResponse>>builder().result(res).build();
    }

}
