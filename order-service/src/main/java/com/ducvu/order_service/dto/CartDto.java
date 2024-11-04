package com.ducvu.order_service.dto;

import com.ducvu.order_service.entity.Cart;
import com.ducvu.order_service.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private Integer cartId;
    private Integer userId;

    @JsonProperty("orders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OrderDto> orderDtos;
}
