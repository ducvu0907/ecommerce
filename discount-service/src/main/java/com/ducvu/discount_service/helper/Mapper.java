package com.ducvu.discount_service.helper;

import com.ducvu.discount_service.dto.response.DiscountResponse;
import com.ducvu.discount_service.entity.Discount;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public DiscountResponse toDiscountResponse(Discount discount) {
        return DiscountResponse.builder()
                .id(discount.getId())
                .type(discount.getType())
                .value(discount.getValue())
                .description(discount.getDescription())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .createdAt(discount.getCreatedAt())
                .updatedAt(discount.getUpdatedAt())
                .build();
    }

}
