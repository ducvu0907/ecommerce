package com.ducvu.cart_service.dto;

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
public class CartDto {
    private Integer id;
    private Integer userId;

    @JsonProperty("items")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CartItemDto> itemDtos;
}
