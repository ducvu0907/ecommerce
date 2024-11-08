package com.ducvu.user_service.helper;

import com.ducvu.user_service.dto.AddressDto;
import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.User;

public class Mapper {

    public static AddressDto map(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .phone(user.getPhone())
                .addressDtos(
                        user.getAddresses()
                                .stream()
                                .map(Mapper::map)
                                .toList()
                )
                .build();
    }

}
