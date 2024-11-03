package com.ducvu.product_service.repository;

import com.ducvu.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where category_id = :categoryId", nativeQuery = true)
    List<Product> findProductsByCategoryId(Integer categoryId);
}
