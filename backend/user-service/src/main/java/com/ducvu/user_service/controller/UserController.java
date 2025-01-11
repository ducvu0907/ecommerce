package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.request.UserUpdateRequest;
import com.ducvu.user_service.dto.ApiResponse;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserResponse> getMyProfile(@RequestHeader("token") String token) {
        var res = userService.getMyProfile(token);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserProfile(@PathVariable("userId") String userId) {
        var res = userService.getUserProfile(userId);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    @PostMapping("/me")
    public ApiResponse<UserResponse> updateMyProfile(@RequestHeader("token") String token, @RequestBody UserUpdateRequest request) {
        var res = userService.updateMyProfile(token, request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

}
