package com.ducvu.user_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String fullName;
    private String email;
    private String username;
    private String role;
    private String phone;

    @JsonProperty("addresses")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressDto> addressDtos;
}
