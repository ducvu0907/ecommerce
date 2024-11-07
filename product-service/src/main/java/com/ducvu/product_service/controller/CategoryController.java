package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    // public
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getCategories() {

    }

    // public
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") String categoryId) {

    }

    // admin
    @PostMapping("")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

    }

    // admin
    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody CategoryDto categoryDto) {

    }

    // admin
    @DeleteMapping("{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryId") String categoryId) {

    }

}
