package com.ducvu.user_service.helper;

import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public User toUser(UserCreateRequest request) {
        return User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public AuthResponse toAuthResponse(User user) {
        return AuthResponse.builder()
                .role(user.getRole())
                .userId(user.getId())
                .build();
    }

    public TokenResponse toTokenResponse(User user) {
        return TokenResponse.builder()
                .token(user.getToken())
                .build();
    }
}
