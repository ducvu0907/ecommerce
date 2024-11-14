package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.AddressDto;
import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
/*    private final UserService userService;

    // public
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
    }

    // admin
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

    }

    // public
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {

    }

    // user
    @GetMapping("/{userId}/addresses")
    public ResponseEntity<UserDto> addAddress(@PathVariable Integer userId, @RequestBody AddressDto addressDto) {

    }

    // user
    @GetMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<UserDto> updateAddress(@PathVariable Integer userId, @PathVariable Integer addressId, @RequestBody AddressDto addressDto) {

    }

    // user
    @GetMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<UserDto> removeAddress(@PathVariable Integer userId, @PathVariable Integer addressId) {

    }

    // user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {

    }

    // admin
    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId) {

    }*/
    @GetMapping("")
    public String test() {
        return "hello world";
    }
}
