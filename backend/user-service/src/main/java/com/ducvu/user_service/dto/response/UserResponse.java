package com.ducvu.user_service.dto.response;

import com.ducvu.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String id;
    String username;
    String fullName;
    String phone;
    Role role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
