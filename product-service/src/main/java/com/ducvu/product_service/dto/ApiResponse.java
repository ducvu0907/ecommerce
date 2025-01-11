package com.ducvu.product_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> { // format for every request response
    @Builder.Default
    private int code = 1000; // default to 1000 - success, otherwise probably 9999
    private String message;
    private T result;
}
