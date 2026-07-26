package com.springboot.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.address.entity.Address;
import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmpId(Long empId); 
}
