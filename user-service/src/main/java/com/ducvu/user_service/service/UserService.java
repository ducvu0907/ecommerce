package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.request.UserUpdateRequest;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private Mapper mapper;

    public UserResponse createUser(UserCreateRequest request) {
        User user = mapper.toUser(request);
        try {
            userRepository.save(user);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return mapper.toUserResponse(user);
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        if (request.getToken() == null) {
            throw new RuntimeException("No token found");
        }
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        if (user.getId() != userId | user.getRole() != "ADMIN") {
            throw new RuntimeException("Unauthorized");
        }

        if (request.getFirstname() != null) {
            user.setFirstName(request.getFirstname());
        }

        if (request.getLastname() != null) {
            user.setFirstName(request.getLastname());
        }

        if (request.getPhone() != null) {
            user.setFirstName(request.getPhone());
        }

        try {
            userRepository.save(user);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return mapper.toUserResponse(user);
    }

    // admin
    public void deleteUser(String userId, AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        if (user.getRole() != "ADMIN") {
            throw new RuntimeException("Unauthorized");
        }
        try {
            userRepository.deleteById(userId);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // admin
    public List<UserResponse> getUsers(AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        if (user.getRole() != "ADMIN") {
            throw new RuntimeException("Unauthorized");
        }
        return userRepository.findAll()
                .stream()
                .map(mapper::toUserResponse)
                .toList();
    }
}
