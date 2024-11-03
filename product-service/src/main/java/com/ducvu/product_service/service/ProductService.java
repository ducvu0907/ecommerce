package com.ducvu.product_service.service;

import com.ducvu.product_service.dto.ProductDto;
import com.ducvu.product_service.entity.Category;
import com.ducvu.product_service.entity.Product;
import com.ducvu.product_service.exception.CategoryNotFoundException;
import com.ducvu.product_service.exception.ProductNotFoundException;
import com.ducvu.product_service.helper.Mapper;
import com.ducvu.product_service.repository.CategoryRepository;
import com.ducvu.product_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(Mapper::map)
                .distinct()
                .toList();
    }

    public List<ProductDto> getAllProductsByCategory(Integer categoryId) {
        Category category =  this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

        return this.productRepository.findProductsByCategoryId(categoryId)
                .stream()
                .map(Mapper::map)
                .distinct()
                .toList();
    }

    public ProductDto getProductById(Integer productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d not found", productId)));

        return Mapper.map(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        if (productDto.getCategoryDto() == null) {
            throw new CategoryNotFoundException("No category provided");
        }
        Integer categoryId = productDto.getCategoryDto().getCategoryId();
        Category category =  this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

        Product product = Mapper.map(productDto);
        this.productRepository.save(product);
        return Mapper.map(product);
    }

    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        if (productDto.getCategoryDto() != null) {
            Integer categoryId = productDto.getCategoryDto().getCategoryId();
            Category category =  this.categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));
        }

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d not found", productId)));

        if (productDto.getProductTitle() != null) {
            product.setProductTitle(productDto.getProductTitle());
        }

        if (productDto.getImageUrl() != null) {
            product.setImageUrl(productDto.getImageUrl());
        }

        if (productDto.getSku() != null) {
            product.setSku(productDto.getSku());
        }

        if (productDto.getPriceUnit() != null) {
            product.setPriceUnit(productDto.getPriceUnit());
        }

        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }

        if (productDto.getCategoryDto() != null) {
            product.setCategory(Mapper.map(productDto.getCategoryDto()));
        }

        this.productRepository.save(product);
        return Mapper.map(product);
    }

    public void deleteProduct(Integer productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d not found", productId)));

        this.productRepository.delete(product);
    }
}
