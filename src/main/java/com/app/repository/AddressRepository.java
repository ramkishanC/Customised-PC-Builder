package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
