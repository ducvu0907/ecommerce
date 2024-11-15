package com.ducvu.user_service.repository;

import com.ducvu.user_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

}
