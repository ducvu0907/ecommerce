package com.ducvu.order_service.helper;

import com.ducvu.order_service.dto.response.OrderItemResponse;
import com.ducvu.order_service.dto.response.OrderResponse;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .buyerId(order.getBuyerId())
                .instruction(order.getInstruction())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .items(
                        order.getItems()
                                .stream()
                                .map(this::toOrderItemResponse)
                                .toList()
                )
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())
                .build();
    }

}
