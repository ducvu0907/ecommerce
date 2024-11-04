package com.ducvu.product_service.controller;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.repository.DtoCollectionResponse;
import com.ducvu.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<DtoCollectionResponse<CategoryDto>> getAllCategories() {
        log.info("Category controller, fetch all categories");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.categoryService.getCategories()));
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") String categoryId) {
        log.info("Category controller, fetch category by id");
        return ResponseEntity.ok(this.categoryService.getCategoryById(Integer.valueOf(categoryId)));
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Category controller, create category");
        return ResponseEntity.ok(this.categoryService.createCategory(categoryDto));
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody CategoryDto categoryDto) {
        log.info("Category controller, update category");
        return ResponseEntity.ok(this.categoryService.updateCategory(Integer.valueOf(categoryId), categoryDto));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryId") String categoryId) {
        log.info("Category controller, delete category");
        this.categoryService.deleteCategory(Integer.valueOf(categoryId));
        return ResponseEntity.ok(true);
    }

}
