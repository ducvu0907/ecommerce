package com.ducvu.order_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductsRequest {
    private boolean isOrderCanceled; // if order is canceled then increase else decrease the quantity
    private List<UpdateProductRequest> products;
}
