package com.ducvu.review_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: change other ids to string
@Document(value = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {
    @Id
    private String id;
    private Integer userId;
    private Integer productId;
    private Integer rating; // {1-5}
    private String content;
}
