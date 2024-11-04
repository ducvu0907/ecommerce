package com.ducvu.user_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String fullName;
    private String email;
    private String phone;

    @JsonProperty("addresses")
    @JsonInclude(value = Include.NON_NULL)
    private Set<AddressDto> addressDtos;

    @JsonProperty("credential")
    @JsonInclude(value = Include.NON_NULL)
    private CredentialDto credentialDto;
}
