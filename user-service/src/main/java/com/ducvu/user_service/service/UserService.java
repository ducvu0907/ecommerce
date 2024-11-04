package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.UserDto;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.exception.EmailAlreadyExistsException;
import com.ducvu.user_service.exception.UserNotFoundException;
import com.ducvu.user_service.exception.UsernameAlreadyExistsException;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.CredentialRepository;
import com.ducvu.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final AddressRepository addressRepository;

    public List<UserDto> getUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(Mapper::map)
                .toList();
    }

    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id: %d not found", userId)));

        return Mapper.map(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = this.userRepository.findByCredentialUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username: %s not found", username)));

        return Mapper.map(user);
    }

    public UserDto createUser(UserDto userDto) {
        String username = userDto.getCredentialDto().getUsername();
        String email = userDto.getEmail();

        this.userRepository.findByCredentialUsername(username)
                .ifPresent(e -> { throw new UsernameAlreadyExistsException("Username already exists"); });

        this.userRepository.findByEmail(email)
                .ifPresent(e -> { throw new EmailAlreadyExistsException("Email already exists"); });

        User user = Mapper.map(userDto);
        userRepository.save(user);

        return Mapper.map(user);
    }

    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id: %d not found", userId)));

        if (userDto.getFullName() != null) {
            user.setFullName(userDto.getFullName());
        }

        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }

        this.userRepository.save(user);
        return Mapper.map(user);
    }

    public void deleteUserById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id: %d not found", userId)));

        this.userRepository.delete(user);
    }

    // TODO: create address, update credential

}
