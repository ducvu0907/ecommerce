package com.ducvu.review_service.helper;

import com.ducvu.review_service.dto.ReviewDto;
import com.ducvu.review_service.entity.Review;

public class Mapper {
    public static ReviewDto map(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .productId(review.getProductId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
