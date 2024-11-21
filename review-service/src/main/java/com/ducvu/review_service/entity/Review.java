package com.ducvu.review_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Review {
    @Id
    private String id;
    private Integer userId;

    @Indexed
    private Integer productId;

    private Integer rating; // 1-5
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
