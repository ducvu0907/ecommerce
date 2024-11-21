package com.ducvu.discount_service.helper;

import com.ducvu.discount_service.dto.response.DiscountResponse;
import com.ducvu.discount_service.entity.Discount;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public DiscountResponse toDiscountResponse(Discount discount) {
        return DiscountResponse.builder()
                .id(discount.getId())
                .description(discount.getDescription())
                .amount(discount.getAmount())
                .percent(discount.getPercent())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .isActive(discount.getIsActive())
                .build();
    }

}
