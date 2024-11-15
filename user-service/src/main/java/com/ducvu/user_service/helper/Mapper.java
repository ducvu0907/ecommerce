package com.ducvu.user_service.helper;

import com.ducvu.user_service.dto.request.AddressCreateRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.response.AddressResponse;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public User toUser(UserCreateRequest request) {
        return User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .addresses(
                        user.getAddresses()
                                .stream()
                                .map(this::toAddressResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public Address toAddress(AddressCreateRequest request) {
        return Address.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .build();
    }

    public AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .street(address.getStreet())
                .city(address.getCity())
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
