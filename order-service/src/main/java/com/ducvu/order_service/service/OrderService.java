package com.ducvu.order_service.service;

import com.ducvu.order_service.config.*;
import com.ducvu.order_service.dto.request.*;
import com.ducvu.order_service.dto.response.*;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;
import com.ducvu.order_service.entity.OrderStatus;
import com.ducvu.order_service.helper.Mapper;
import com.ducvu.order_service.repository.OrderItemRepository;
import com.ducvu.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Mapper mapper;
    private final UserClient userClient;
    private final CartClient cartClient;
    private final DiscountClient discountClient;
    private final ProductClient productClient;

    public List<OrderResponse> getMyOrders(String token) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        return orderRepository.findByBuyerId(userId)
                .stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse getOrder(String token, String orderId) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!userId.equals(order.getBuyerId())) {
            throw new RuntimeException("Unauthorized");
        }

        return mapper.toOrderResponse(order);
    }

    // TODO
    @Transactional
    public OrderResponse createOrder(String token, CreateOrderRequest request) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Order order = new Order();

        // set from request
        order.setAddress(request.getAddress());
        order.setInstruction(request.getInstruction());
        order.setDiscountId(request.getDiscountId());

        // default status
        order.setStatus(OrderStatus.PENDING);

        // map each cart item to order item
        var cartResponse = getCartResponse(token);
        cartResponse.getItems().stream().forEach(item -> {

        });

        // use discount
        var discountResponse = getDiscountResponse(request.getDiscountId());
    }

    // TODO
    @Transactional
    public void cancelOrder(String token, String orderId) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals(OrderStatus.PENDING)) {
            throw new RuntimeException("Order is not cancellable");
        }

    }

    private DiscountResponse getDiscountResponse(String discountId) {
        var discountResponse = discountClient.getDiscount(discountId);
        if (discountResponse.getResult() == null) {
            throw new RuntimeException("Discount not found");
        }
        return discountResponse.getResult();
    }

    private CartResponse getCartResponse(String token) {
        var cartResponse = cartClient.getMyCart(token);
        if (cartResponse.getResult() == null) {
            throw new RuntimeException("Cart not found");
        }
        return cartResponse.getResult();
    }

    private AuthResponse validateToken(String token) {
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }
        return authResponse.getResult();
    }
}
