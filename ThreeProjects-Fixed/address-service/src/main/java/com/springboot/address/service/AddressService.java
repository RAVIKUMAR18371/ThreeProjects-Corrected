package com.springboot.address.service;

import com.springboot.address.dto.AddressDTO;
import com.springboot.address.dto.AddressRequestDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> saveAddresses(Long empId, List<AddressRequestDTO> requestDtos);
    List<AddressDTO> updateAddresses(Long empId, List<AddressRequestDTO> requestDtos);
}
