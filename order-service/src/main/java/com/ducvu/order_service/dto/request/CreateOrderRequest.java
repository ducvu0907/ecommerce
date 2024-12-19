package com.ducvu.order_service.dto.request;

import com.ducvu.order_service.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {
    String token;
    String description;
    String discountId;
    List<OrderItemRequest> items;
}
