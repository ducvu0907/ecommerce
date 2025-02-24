package com.ducvu.user_service.dto.request;

import com.ducvu.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    String username;
    String email;
    String fullName;
    String password;
    String phone;
    Role role;
}
