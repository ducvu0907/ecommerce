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

    @Transactional
    public OrderResponse createOrder(String token, CreateOrderRequest request) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Order order = new Order();

        // set fields from request
        order.setBuyerId(userId);
        order.setAddress(request.getAddress());
        order.setInstruction(request.getInstruction());
        order.setDiscountId(request.getDiscountId());

        // default initial status
        order.setStatus(OrderStatus.PENDING);

        // total price and shipment declaration
        double totalPrice = 0.0;
        List<ShipmentItemRequest> shipmentItemsRequest = new ArrayList<>();

        // map each cart item to order item
        var cartResponse = getCartResponse(token);

        // loop
        for (CartItemResponse item : cartResponse.getItems()) {
            totalPrice += item.getSubtotal();

            // map cart item to order item
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .subtotal(item.getSubtotal())
                    .build();

            // shipment request
            ShipmentItemRequest shipmentItemRequest = ShipmentItemRequest.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();

            orderItemRepository.save(orderItem);
            shipmentItemsRequest.add(shipmentItemRequest);
        }

        // set total price
        order.setTotalPrice(totalPrice);

        // apply discount
        if (request.getDiscountId() != null) {
            var discountResponse = getDiscountResponse(request.getDiscountId());
            order.setDiscountId(discountResponse.getId());
            // discount type is fixed or percentage
            if ("FIXED".equals(discountResponse.getType())) {
                order.setTotalPrice(totalPrice - discountResponse.getValue());
            } else {
                order.setTotalPrice(totalPrice * (1 - discountResponse.getValue() / 100));
            }
        }

        var result = orderRepository.save(order);

        // send shipment request
        var shipmentRequest = ShipmentRequest.builder()
                .orderId(result.getId())
                .products(shipmentItemsRequest)
                .build();
        var shipmentResponse = productClient.planOrderShipment(shipmentRequest);
        if (shipmentResponse.getResult() == null) {
            throw new RuntimeException("Place order unsuccessfully");
        }

        return mapper.toOrderResponse(result);
    }

    @Transactional
    public void cancelOrder(String token, String orderId) {
        var authResponse = validateToken(token);
        String userId = authResponse.getUserId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getBuyerId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        // if status is not pending then cannot cancel order
        if (!order.getStatus().equals(OrderStatus.PENDING)) {
            throw new RuntimeException("Order is not cancellable");
        }

        order.setStatus(OrderStatus.CANCELLED);

        // update inventories
        var cancelResponse = productClient.cancelOrderShipment(orderId);
        if (cancelResponse.getResult() == null) {
            throw new RuntimeException("Cancel order unsuccessfully");
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
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("No token provided");
        }
        var authResponse = userClient.authenticate(token);
        if (authResponse.getResult() == null) {
            throw new RuntimeException("Token invalid");
        }
        return authResponse.getResult();
    }
}
