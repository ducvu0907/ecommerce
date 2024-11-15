package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.AddressCreateRequest;
import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.response.AddressResponse;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public AddressResponse createAddress(AddressCreateRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        Address address = mapper.toAddress(request);
        address.setUser(user);
        addressRepository.save(address);
        user.getAddresses().add(address);
        userRepository.save(user);

        return mapper.toAddressResponse(address);
    }

    public void deleteAddress(Integer addressId, AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        Address address = addressRepository.findById(addressId)
                        .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Address not found");
        }
        addressRepository.delete(address);
        user.getAddresses().remove(address);
        userRepository.save(user);
    }
}
