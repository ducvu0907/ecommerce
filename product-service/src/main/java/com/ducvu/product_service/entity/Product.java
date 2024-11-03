package com.ducvu.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "seller_id", nullable = false)
    private Integer sellerId;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "price_unit", nullable = false)
    private Double priceUnit;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

}
