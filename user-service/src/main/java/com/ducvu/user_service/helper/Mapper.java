package com.ducvu.user_service.helper;

import com.ducvu.user_service.dto.AddressDto;
import com.ducvu.user_service.dto.CredentialDto;
import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.Credential;
import com.ducvu.user_service.entity.User;

import java.util.stream.Collectors;

public class Mapper {

    public static AddressDto map(Address address) {
        return AddressDto.builder()
                .addressId(address.getAddressId())
                .country(address.getCountry())
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static Address map(AddressDto addressDto) {
        return Address.builder()
                .country(addressDto.getCountry())
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .build();
    }

    public static CredentialDto map(Credential credential) {
        return CredentialDto.builder()
                .credentialId(credential.getCredentialId())
                .username(credential.getUsername())
                .role(credential.getRole())
                .isEnabled(credential.getIsEnabled())
                .build();
    }

    public static Credential map(CredentialDto credentialDto) {
        return Credential.builder()
                .username(credentialDto.getUsername())
                .password(credentialDto.getPassword())
                .role(credentialDto.getRole())
                .isEnabled(credentialDto.getIsEnabled())
                .build();
    }

    public static UserDto map(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .addressDtos(
                        user.getAddresses()
                                .stream()
                                .map(Mapper::map)
                                .collect(Collectors.toSet())
                )
                .credentialDto(map(user.getCredential()))
                .build();
    }

    public static User map(UserDto userDto) {
        return User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .credential(map(userDto.getCredentialDto()))
                .build();
    }

}
