package com.ducvu.order_service.service;

import com.ducvu.order_service.dto.OrderDto;
import com.ducvu.order_service.repository.OrderItemRepository;
import com.ducvu.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<OrderDto> getOrdersByUserId(Integer userId) {

    }

    public OrderDto getOrderById(Integer orderId) {

    }

    public OrderDto createOrder(OrderDto orderDto) {

    }

    public boolean deleteOrder(Integer orderId) {

    }
}
