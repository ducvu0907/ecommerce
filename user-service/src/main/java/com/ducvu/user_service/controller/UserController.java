package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.request.UserUpdateRequest;
import com.ducvu.user_service.dto.response.ApiResponse;
import com.ducvu.user_service.dto.response.AuthResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // user
    @PostMapping("/me")
    public ApiResponse<UserResponse> getMyProfile(@RequestBody AuthRequest request) {
        var res = userService.getMyProfile(request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    // user
    @PostMapping("/me/update")
    public ApiResponse<UserResponse> updateMyProfile(@RequestBody UserUpdateRequest request) {
        var res = userService.updateMyProfile(request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    // public
    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserProfile(@PathVariable("userId") Integer userId) {
        var res = userService.getUserProfile(userId);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

}
