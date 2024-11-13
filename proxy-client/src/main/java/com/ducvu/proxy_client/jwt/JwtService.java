package com.ducvu.proxy_client.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;

    public String extractUsername(String token) {
        log.info("Jwt service extracts username from given token");
        return jwtUtil.extractUsername(token);
    }

    public Date extractExpiration(String token) {
        log.info("Jwt service extracts expiration from given token");
        return jwtUtil.extractExpiration(token);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        log.info("Jwt service extracts claims from given token");
        return jwtUtil.extractClaims(token, claimsTFunction);
    }

    public String generateToken(UserDetails userDetails) {
        log.info("Jwt service generates token from given user details");
        return jwtUtil.generateToken(userDetails);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        log.info("Jwt service validates given token");
        return jwtUtil.isTokenValid(token, userDetails);
    }
}
