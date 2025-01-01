package com.ducvu.order_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"order"})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
