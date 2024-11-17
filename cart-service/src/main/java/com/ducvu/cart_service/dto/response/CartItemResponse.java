package com.ducvu.cart_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {
    Integer id;
    Integer productId;
    Integer quantity;
    Double price;
    Integer cartId;
}
