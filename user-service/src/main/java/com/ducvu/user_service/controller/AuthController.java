package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.request.LoginRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.ApiResponse;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.TokenResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.service.AuthService;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        var res = authService.login(request);
        return ApiResponse.<TokenResponse>builder().result(res).build();
    }

    @PostMapping("/signup")
    public ApiResponse<UserResponse> signup(@RequestBody UserCreateRequest request) {
        var res = userService.createUser(request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    @GetMapping("/validate")
    public ApiResponse<AuthResponse> authenticate(@RequestHeader("token") String token) {
        var res = authService.validate(token);
        return ApiResponse.<AuthResponse>builder().result(res).build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("token") String token) {
        authService.logout(token);
        return ApiResponse.<String>builder().result("Logout successfully").build();
    }
}
