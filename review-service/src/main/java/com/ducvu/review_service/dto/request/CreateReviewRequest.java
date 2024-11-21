package com.ducvu.review_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateReviewRequest {
    String token;
    Integer productId;
    Integer rating;
    String content;
}