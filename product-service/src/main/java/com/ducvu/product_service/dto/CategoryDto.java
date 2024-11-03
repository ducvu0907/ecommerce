package com.ducvu.product_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String categoryTitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ProductDto> productDtos;
}
