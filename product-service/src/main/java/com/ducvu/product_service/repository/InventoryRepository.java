package com.ducvu.product_service.repository;

import com.ducvu.product_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByProductId(String productId);
}
