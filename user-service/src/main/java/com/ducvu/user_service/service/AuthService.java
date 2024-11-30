package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.request.LoginRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.helper.TokenUtil;
import com.ducvu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final TokenUtil tokenUtil;

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        return mapper.toAuthResponse(user);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username invalid"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        // reset token every login
        String newToken = tokenUtil.generateToken();
        user.setToken(newToken);
        userRepository.save(user);

        return mapper.toTokenResponse(user);
    }

    public void logout(AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        // invalidate the current token
        user.setToken(null);
        userRepository.save(user);
    }
}
