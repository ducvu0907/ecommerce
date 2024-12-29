package com.ducvu.review_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    String id;
    String userId;
    String productId;
    Integer rating;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
