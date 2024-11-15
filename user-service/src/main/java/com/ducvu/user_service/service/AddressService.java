package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.request.AddressCreateRequest;
import com.ducvu.user_service.dto.request.AuthRequest;
import com.ducvu.user_service.dto.response.AddressResponse;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.entity.User;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.AddressRepository;
import com.ducvu.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private Mapper mapper;

    public AddressResponse createAddress(AddressCreateRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        Address address = mapper.toAddress(request);

        addressRepository.save(address);
        user.getAddresses().add(address);
        userRepository.save(user);

        return mapper.toAddressResponse(address);
    }

    public void deleteAddress(String addressId, AuthRequest request) {
        User user = userRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token invalid"));

        Address address = user.getAddresses()
                .stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));

        addressRepository.delete(address);
        user.getAddresses().remove(address);
        userRepository.save(user);
    }
}
