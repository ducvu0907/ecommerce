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
                .isPaid(order.getIsPaid())
                .status(order.getStatus())
                .itemDtos(
                        order.getItems()
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

}
