package com.ducvu.payment_service.config;

import com.ducvu.payment_service.dto.ApiResponse;
import com.ducvu.payment_service.dto.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {
    @GetMapping("/auth/validate")
    ApiResponse<AuthResponse> authenticate(@RequestHeader("token") String token);
}
