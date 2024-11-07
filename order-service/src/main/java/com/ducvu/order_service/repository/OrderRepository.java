package com.ducvu.order_service.repository;

import com.ducvu.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select * from order where user_id = :userId", nativeQuery = true)
    List<Order> findOrdersByUserId(Integer userId);
}
