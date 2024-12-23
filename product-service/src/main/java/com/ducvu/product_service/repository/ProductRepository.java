package com.ducvu.product_service.repository;

import com.ducvu.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findBySellerId(Integer sellerId);
    Optional<Product> findBySku(String sku);

    @Query(value = "SELECT * FROM products p " +
            "WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))",
            nativeQuery = true)
    List<Product> searchProducts(String query);
}
