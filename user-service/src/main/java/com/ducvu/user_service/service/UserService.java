package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.request.UserCreateRequest;
import com.ducvu.user_service.dto.request.UserUpdateRequest;
import com.ducvu.user_service.dto.response.UserResponse;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.Role;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.helper.TokenUtil;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final Mapper mapper;
    private final TokenUtil tokenUtil;

    @Transactional // make sure seller with no address shouldn't be saved
    public UserResponse createUser(UserCreateRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> { throw new RuntimeException("User already exists"); });

        User user = mapper.toUser(request);
        user.setAddresses(new HashSet<>());

        if (Role.SELLER.equals(user.getRole())) {
            if (request.getAddress() == null) {
                throw new RuntimeException("Seller must provide an address");
            }
            user = userRepository.save(user);
            Address address = mapper.toAddress(request.getAddress());
            address.setUser(user);
            address = addressRepository.save(address);
            user.getAddresses().add(address);
        }

        userRepository.save(user);
        return mapper.toUserResponse(user);
    }

    public UserResponse getUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toUserResponse(user);
    }

    public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
        if (request.getToken() == null) {
            throw new RuntimeException("No token found");
        }
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        if (!user.getId().equals(userId) | !user.getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }

        if (request.getFirstname() != null) {
            user.setFirstName(request.getFirstname());
        }

        if (request.getLastname() != null) {
            user.setFirstName(request.getLastname());
        }

        if (request.getPhone() != null) {
            user.setFirstName(request.getPhone());
        }

        try {
            userRepository.save(user);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return mapper.toUserResponse(user);
    }

    // admin
    public void deleteUser(Integer userId, AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        if (!user.getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }
        try {
            userRepository.deleteById(userId);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // admin
    public List<UserResponse> getUsers(AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        if (!user.getRole().equals("admin")) {
            throw new RuntimeException("Unauthorized");
        }
        return userRepository.findAll()
                .stream()
                .map(mapper::toUserResponse)
                .toList();
    }

    public UserResponse getMyProfile(AuthRequest request) {
        if (request.getToken() == null) {
            throw new RuntimeException("No token found");
        }
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        return mapper.toUserResponse(user);
    }

    public UserResponse updateMyProfile(UserUpdateRequest request) {
        if (request.getToken() == null) {
            throw new RuntimeException("No token found");
        }
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        if (request.getFirstname() != null) {
            user.setFirstName(request.getFirstname());
        }

        if (request.getLastname() != null) {
            user.setFirstName(request.getLastname());
        }

        if (request.getPhone() != null) {
            user.setFirstName(request.getPhone());
        }

        userRepository.save(user);
        return mapper.toUserResponse(user);
    }

    public UserResponse getUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapper.toUserResponse(user);
    }

}
