package com.ducvu.user_service.dto;

import com.ducvu.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialDto {
    private Integer credentialId;
    private String username;
    private String password;
    private Role role;
    private Boolean isEnabled;
}
