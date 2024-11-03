package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.CategoryDto;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.exception.CategoryNotFoundException;
import com.ducvu.product_service.helper.Mapper;
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

    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(Mapper::map)
                .distinct()
                .toList();
    }

    public CategoryDto getCategoryById(Integer categoryId) {
        Category category =  this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

        return Mapper.map(category);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        return Mapper.map(categoryRepository.save(Mapper.map(categoryDto)));
    }

    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

        if(categoryDto.getCategoryTitle() != null) {
            category.setCategoryTitle(category.getCategoryTitle());
        }

        this.categoryRepository.save(category);
        return Mapper.map(category);
    }

    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

        this.categoryRepository.delete(category);
    }
}
