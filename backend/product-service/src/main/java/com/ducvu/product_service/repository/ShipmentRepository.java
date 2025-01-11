package com.ducvu.product_service.repository;

import com.ducvu.product_service.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
    List<Shipment> findByOrderId(String orderId);
}
