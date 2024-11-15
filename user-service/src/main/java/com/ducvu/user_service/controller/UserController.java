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

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        var res = userService.createUser(request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(@RequestBody AuthRequest request) {
        var res = userService.getUsers(request);
        return ApiResponse.<List<UserResponse>>builder().result(res).build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        var res = userService.getUser(Integer.parseInt(userId));
        return ApiResponse.<UserResponse>builder().result(res).build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId, @RequestBody AuthRequest request) {
        userService.deleteUser(Integer.parseInt(userId), request);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        var res = userService.updateUser(Integer.parseInt(userId), request);
        return ApiResponse.<UserResponse>builder().result(res).build();
    }
}
