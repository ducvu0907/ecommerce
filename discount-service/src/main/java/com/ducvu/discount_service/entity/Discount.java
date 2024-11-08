package com.ducvu.discount_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "discounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Discount extends BaseEntity {
    @Id
    private String id;
    private String type;
    private String description;
    private Double value;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer usageLimit;
    private Double minOrder;
    private Integer productId;
    private String promoCode;
    private Boolean isActive;
}
