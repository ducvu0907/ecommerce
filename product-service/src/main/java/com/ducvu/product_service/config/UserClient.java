package com.ducvu.product_service.config;

import com.ducvu.product_service.dto.ApiResponse;
import com.ducvu.product_service.dto.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8007")
public interface UserClient {
    @GetMapping("/auth/validate")
    ApiResponse<AuthResponse> authenticate(@RequestHeader("token") String token);
}
