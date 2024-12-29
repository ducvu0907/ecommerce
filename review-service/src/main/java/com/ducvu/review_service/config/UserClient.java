package com.ducvu.review_service.config;

import com.ducvu.review_service.dto.ApiResponse;
import com.ducvu.review_service.dto.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8007")
public interface UserClient {
    @PostMapping("/auth/validate")
    ApiResponse<AuthResponse> authenticate(@RequestHeader("token") String token);
}
