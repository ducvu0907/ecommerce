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
public class UserCreateRequest { // both registration and user creation
    String username;
    String password;
    String firstName;
    String lastName;
    String phone;
    Role role;
}
