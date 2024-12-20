package com.ducvu.user_service.controller;

import com.ducvu.user_service.dto.request.AddressCreateRequest;
import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.response.AddressResponse;
import com.ducvu.user_service.dto.response.ApiResponse;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.service.AddressService;
import com.ducvu.user_service.service.AuthService;
import com.ducvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    // user
    @PostMapping("/")
    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressCreateRequest request) {
        var res = addressService.createAddress(request);
        return ApiResponse.<AddressResponse>builder().result(res).build();
    }

    // user
    @DeleteMapping("/{addressId}")
    public ApiResponse<String> deleteAddress(@PathVariable("addressId") Integer addressId, @RequestBody AuthRequest request) {
        addressService.deleteAddress(addressId, request);
        return ApiResponse.<String>builder().result("Address has been deleted").build();
    }
}
