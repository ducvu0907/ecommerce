package com.ducvu.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private boolean isOrderCanceled; // if order is canceled then increase else decrease the quantity
    private List<OrderProductRequest> products;
}
