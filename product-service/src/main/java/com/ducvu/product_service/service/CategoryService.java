package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategories() {

    }

    public CategoryDto getCategoryById(Integer categoryId) {

    }

    public CategoryDto createCategory(CategoryDto categoryDto) {

    }

    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {

    }

    public void deleteCategory(Integer categoryId) {

    }
}
