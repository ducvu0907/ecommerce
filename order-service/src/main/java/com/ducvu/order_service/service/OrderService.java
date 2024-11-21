package com.ducvu.order_service.service;

import com.ducvu.order_service.config.CartClient;
import com.ducvu.order_service.config.UserClient;
import com.ducvu.order_service.dto.request.AuthRequest;
import com.ducvu.order_service.dto.request.CreateOrderRequest;
import com.ducvu.order_service.dto.response.OrderItemResponse;
import com.ducvu.order_service.dto.response.OrderResponse;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;
import com.ducvu.order_service.helper.Mapper;
import com.ducvu.order_service.repository.OrderItemRepository;
import com.ducvu.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public List<OrderResponse> getMyOrders(AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        List<Order> orders = orderRepository.findByUserId(authResponse.getResult().getUserId());
        return orders.stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse getOrder(Integer orderId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        return mapper.toOrderResponse(order);
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        AuthRequest authRequest = AuthRequest.builder().token(request.getToken()).build();
        var authResponse = userClient.authenticate(authRequest);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        var cartResponse = cartClient.getMyCart(authRequest);
        if (cartResponse == null) {
            throw new RuntimeException("Cart not found");
        }

        Order order = new Order();
        order.setItems(new HashSet<>());
        order.setUserId(authResponse.getResult().getUserId());

        if (request.getDescription() != null) {
            order.setDescription(request.getDescription());
        }

        double totalAmount = 0.0;
        for (var i : cartResponse.getResult().getItems()) {
            totalAmount += i.getPrice();
            OrderItem item = mapper.toOrderItem(i);
            item.setOrder(order);
            orderItemRepository.save(item);
            order.getItems().add(item);
        }

        order.setTotalAmount(totalAmount);
        order.setStatus("pending"); // default status
        orderRepository.save(order);

        var deleteItemsResponse = cartClient.deleteItems(authRequest); // empty the cart
        log.info(deleteItemsResponse.getResult());

        return mapper.toOrderResponse(order);
    }

    public void cancelOrder(Integer orderId, AuthRequest request) {
        var authResponse = userClient.authenticate(request);
        if (authResponse == null) {
            throw new RuntimeException("Token invalid");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(authResponse.getResult().getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        orderRepository.delete(order);
    }

}
