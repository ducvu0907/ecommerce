package com.ducvu.order_service.dto;

import com.ducvu.order_service.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String description;
    private Double totalAmount;

    @JsonProperty("items")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderItemDto> items;
}
