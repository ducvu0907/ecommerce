package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.request.LoginRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.response.ApiResponse;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.service.AuthService;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    // public
    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        var res = authService.login(request);
        return ApiResponse.<TokenResponse>builder().result(res).build();
    }

    // public
    @PostMapping("/signup")
    public ApiResponse<UserResponse> signup(@RequestBody UserCreateRequest request) {
        var res = userService.createUser(request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    // user
    @PostMapping("/token")
    public ApiResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        var res = authService.authenticate(request);
        return ApiResponse.<AuthResponse>builder().result(res).build();
    }

    // user
    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody AuthRequest request) {
        authService.logout(request);
        return ApiResponse.<String>builder().result("Logout successfully").build();
    }
}
