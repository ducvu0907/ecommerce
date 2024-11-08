package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.AddressDto;
import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserDto createUser(UserDto userDto) {

    }

    public List<UserDto> getUsers() {

    }

    public UserDto getUser(Integer userId) {

    }

    public UserDto addAddress(Integer userId, AddressDto addressDto) {

    }

    public UserDto updateAddress(Integer userId, Integer addressId, AddressDto addressDto) {

    }

    public UserDto removeAddress(Integer userId, Integer addressId) {

    }

    public UserDto updateUser(Integer userId, UserDto userDto) {

    }

    public void deleteUser(Integer userId) {

    }
}
