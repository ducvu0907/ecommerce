package com.ducvu.user_service.dto.response;

import com.ducvu.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    Integer id;
    String username;
    String firstName;
    String lastName;
    String phone;
    Set<AddressResponse> addresses;
    Role role;
}
