package com.ducvu.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products", indexes = {
        // TODO: remove this when changing search engine
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_description", columnList = "description")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"category"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sellerId;
    private String title;
    private String description;
    private String imageUrl;
    private String sku;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
