package com.ducvu.product_service.exception;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private Integer status;
}
