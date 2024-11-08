package com.ducvu.discount_service.helper;

import com.ducvu.discount_service.dto.DiscountDto;
import com.ducvu.discount_service.entity.Discount;

public class Mapper {
    public static DiscountDto map(Discount discount) {
        return DiscountDto.builder()
                .id(discount.getId())
                .type(discount.getType())
                .description(discount.getDescription())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .usageLimit(discount.getUsageLimit())
                .minOrder(discount.getMinOrder())
                .promoCode(discount.getPromoCode())
                .productId(discount.getProductId())
                .isActive(discount.getIsActive())
                .build();
    }
}
