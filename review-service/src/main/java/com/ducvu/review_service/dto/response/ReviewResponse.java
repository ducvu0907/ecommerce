package com.ducvu.review_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    String id;
    Integer userId;
    Integer productId;
    Integer rating;
    String content;
    LocalDate createdAt;
    LocalDate updatedAt;
}
