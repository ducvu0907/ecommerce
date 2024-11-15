package com.ducvu.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "addresses")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude ={ "user" })
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;
    private String street;
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
