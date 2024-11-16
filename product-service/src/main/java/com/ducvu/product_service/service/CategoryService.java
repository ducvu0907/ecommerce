package com.ducvu.product_service.service;

import com.ducvu.product_service.config.UserClient;
import com.ducvu.product_service.dto.request.AuthRequest;
import com.ducvu.product_service.dto.request.CategoryCreateRequest;
import com.ducvu.product_service.dto.response.AuthResponse;
import com.ducvu.product_service.dto.response.CategoryResponse;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.helper.Mapper;
import com.ducvu.product_service.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;
    private final UserClient userClient;

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapper.toCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryCreateRequest request) {
        // check user role
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null || !authResponse.getResult().getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }

        categoryRepository.findByTitle(request.getTitle())
                .ifPresent(category -> {throw new RuntimeException("Category already exists");});

        Category category = mapper.toCategory(request);
        category.setProducts(new HashSet<>());
        categoryRepository.save(category);

        return mapper.toCategoryResponse(category);
    }

}
