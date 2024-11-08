package com.ducvu.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private String id;
    private Integer userId;
    private Integer productId;
    private Integer rating;
    private String content;
    private LocalDateTime createdAt;
}
