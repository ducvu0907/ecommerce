package com.ducvu.review_service.helper;

import com.ducvu.review_service.dto.response.ReviewResponse;
import com.ducvu.review_service.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .productId(review.getProductId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

}
