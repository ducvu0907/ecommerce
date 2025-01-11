package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.request.UserUpdateRequest;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserResponse createUser(UserCreateRequest request) {
        log.info("User service; Creating user");

        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> { throw new RuntimeException("User already exists"); });

        User user = mapper.toUser(request);

        userRepository.save(user);
        return mapper.toUserResponse(user);
    }

    public UserResponse getMyProfile(String token) {
        log.info("User service; Get my profile");

        if (token == null) {
            throw new RuntimeException("No token found");
        }

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toUserResponse(user);
    }

    public UserResponse getUserProfile(String userId) {
        log.info("User service; Get user profile");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toUserResponse(user);
    }

    public UserResponse updateMyProfile(String token, UserUpdateRequest request) {
        log.info("User service; Update my profile");

        if (token == null) {
            throw new RuntimeException("No token found");
        }

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        userRepository.save(user);

        return mapper.toUserResponse(user);
    }

}
