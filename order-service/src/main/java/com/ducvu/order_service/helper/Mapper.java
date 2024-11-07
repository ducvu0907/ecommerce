package com.ducvu.order_service.helper;

import com.ducvu.order_service.dto.OrderDto;
import com.ducvu.order_service.dto.OrderItemDto;
import com.ducvu.order_service.entity.Order;
import com.ducvu.order_service.entity.OrderItem;

public class Mapper {
    public static OrderDto map(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .description(order.getDescription())
                .totalAmount(order.getTotalAmount())
                .items(order.getItems()
                        .stream().map(Mapper::map)
                        .toList()
                )
                .build();
    }

    public static Order map(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .description(orderDto.getDescription())
                .totalAmount(orderDto.getTotalAmount())
                .items(orderDto.getItems()
                        .stream().map(Mapper::map)
                        .toList()
                )
                .build();
    }

    public static OrderItemDto map(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

    public static OrderItem map(OrderItemDto itemDto) {
        return OrderItem.builder()
                .id(itemDto.getId())
                .productId(itemDto.getProductId())
                .quantity(itemDto.getQuantity())
                .price(itemDto.getPrice())
                .build();
    }

}
