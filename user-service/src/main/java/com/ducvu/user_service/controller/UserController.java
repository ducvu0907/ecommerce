package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.DtoCollectionResponse;
import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<UserDto>> getUsers() {
        log.info("User controller, fetch all users");
        return ResponseEntity.ok(new DtoCollectionResponse<>(userService.getUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        log.info("User controller, fetch user by id");
        return ResponseEntity.ok(userService.getUserById(Integer.valueOf(userId)));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("User controller, create user");
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        log.info("User controller, update user");
        return ResponseEntity.ok(userService.updateUser(Integer.valueOf(userId), userDto));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        log.info("User controller, fetch user by username");
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) {
        log.info("User controller, delete user");
        userService.deleteUserById(Integer.valueOf(userId));
        return ResponseEntity.ok(true);
    }
}
