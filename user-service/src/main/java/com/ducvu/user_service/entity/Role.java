package com.ducvu.user_service.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_ADMIN("ADMIN"), ROLE_PM("PRODUCT MANAGER"), ROLE_USER("USER");
    private final String role;
}
