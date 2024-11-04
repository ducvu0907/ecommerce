package com.ducvu.user_service.dto;

import com.ducvu.user_service.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private Integer addressId;
    private String country;
    private String street;
    private String city;
    private String postalCode;

    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
