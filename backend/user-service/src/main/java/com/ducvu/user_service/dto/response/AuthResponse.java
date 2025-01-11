package com.ducvu.user_service.dto.response;

import com.ducvu.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse { // authenticate the token sent from external service
    String userId;
    Role role;
}
