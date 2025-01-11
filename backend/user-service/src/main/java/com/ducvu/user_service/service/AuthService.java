package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.LoginRequest;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
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

    public AuthResponse validate(String token) {
        log.info("Auth service; validate");

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        return mapper.toAuthResponse(user);
    }

    public TokenResponse login(LoginRequest request) {
        log.info("Auth service; login");

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username invalid"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        // reset token on login
        String newToken = tokenUtil.generateToken();
        user.setToken(newToken);
        userRepository.save(user);

        return mapper.toTokenResponse(user);
    }

    public void logout(String token) {
        log.info("Auth service; logout");

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        // invalidate the token
        user.setToken(null);
        userRepository.save(user);
    }
}
