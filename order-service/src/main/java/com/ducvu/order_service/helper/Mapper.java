package com.ducvu.order_service.helper;

import com.ducvu.order_service.dto.response.CartItemResponse;
import com.ducvu.order_service.dto.response.OrderItemResponse;
import com.ducvu.order_service.dto.response.OrderResponse;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .description(order.getDescription())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(
                        order.getItems()
                                .stream()
                                .map(this::toOrderItemResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .orderId(item.getOrder().getId())
                .build();
    }

    public OrderItem toOrderItem(CartItemResponse itemResponse) {
        return OrderItem.builder()
                .productId(itemResponse.getProductId())
                .quantity(itemResponse.getQuantity())
                .price(itemResponse.getPrice())
                .build();
    }
}
