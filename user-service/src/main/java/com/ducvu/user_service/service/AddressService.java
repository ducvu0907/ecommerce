package com.ducvu.user_service.service;

import com.ducvu.user_service.dto.AddressDto;
import com.ducvu.user_service.entity.Address;
import com.ducvu.user_service.exception.AddressNotFoundException;
import com.ducvu.user_service.helper.Mapper;
import com.ducvu.user_service.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<AddressDto> getAddresses() {
        return this.addressRepository.findAll()
                .stream()
                .map(Mapper::map)
                .toList();
    }

    public AddressDto getAddressById(Integer addressId) {
        Address address = this.addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", addressId)));

        return Mapper.map(address);
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Address address = Mapper.map(addressDto);
        this.addressRepository.save(address);
        return Mapper.map(address);
    }

    public AddressDto updateAddress(Integer addressId, AddressDto addressDto) {
        Address address = this.addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", addressId)));

        if (addressDto.getCountry() != null) {
            address.setCountry(addressDto.getCountry());
        }

        if (addressDto.getStreet() != null) {
            address.setStreet(addressDto.getStreet());
        }

        if (addressDto.getCity() != null) {
            address.setCity(addressDto.getCity());
        }

        if (addressDto.getPostalCode() != null) {
            address.setPostalCode(addressDto.getPostalCode());
        }

        this.addressRepository.save(address);
        return Mapper.map(address);
    }

    public void deleteAddressById(Integer addressId) {
        Address address = this.addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", addressId)));

        this.addressRepository.delete(address);
    }
}
