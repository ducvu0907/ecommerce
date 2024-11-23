package com.ducvu.payment_service.config;

import com.ducvu.payment_service.dto.request.AuthRequest;
import com.ducvu.payment_service.dto.response.ApiResponse;
import com.ducvu.payment_service.dto.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service", url = "http://localhost:8007")
public interface UserClient {
    @PostMapping(value = "/auth/token", consumes = "application/json")
    ApiResponse<AuthResponse> authenticate(AuthRequest request);
}
