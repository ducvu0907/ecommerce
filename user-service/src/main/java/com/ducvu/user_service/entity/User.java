package com.ducvu.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = "addresses")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    private String firstName;
    private String lastName;

    private String password;
    private String role; // admin, seller, user
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @Column(name = "token", unique = true)
    private String token;
}
