package com.springboot.address.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.address.dto.AddressDTO;
import com.springboot.address.dto.AddressRequestDTO;
import com.springboot.address.entity.Address;
import com.springboot.address.exception.BadRequestException;
import com.springboot.address.feign.EmployeeFeignClient;
import com.springboot.address.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ModelMapper modelMapper;
    private final EmployeeFeignClient employeeFeignClient;

    public AddressServiceImpl(AddressRepository repository,
                              ModelMapper modelMapper,
                              EmployeeFeignClient employeeFeignClient) {

        this.repository = repository;
        this.modelMapper = modelMapper;
        this.employeeFeignClient = employeeFeignClient;
    }

    @Override
    public List<AddressDTO> saveAddresses(Long empId, List<AddressRequestDTO> requestDtos) {

        // Verify Employee Exists
        employeeFeignClient.getEmployeeById(empId);

        List<Address> savedAddresses = saveOrUpdateBatchHelper(empId, requestDtos);

        return savedAddresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> updateAddresses(Long empId, List<AddressRequestDTO> requestDtos) {

        // Verify Employee Exists
        employeeFeignClient.getEmployeeById(empId);

        boolean missingId = requestDtos.stream()
                .anyMatch(dto -> dto.getId() == null);

        if (missingId) {
            throw new BadRequestException(
                    "Each address must include an existing id to be updated");
        }

        List<Address> updatedAddresses =
                saveOrUpdateBatchHelper(empId, requestDtos);

        return updatedAddresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    private List<Address> saveOrUpdateBatchHelper(
            Long empId,
            List<AddressRequestDTO> dtos) {

        List<Address> addressEntities = dtos.stream()
                .map(dto -> {

                    Address address;

                    if (dto.getId() != null) {

                        address = repository.findById(dto.getId())
                                .orElseThrow(() ->
                                        new RuntimeException("Address not found"));

                    } else {

                        address = new Address();

                    }

                    modelMapper.map(dto, address);

                    address.setEmpId(empId);

                    return address;

                })
                .collect(Collectors.toList());

        return repository.saveAll(addressEntities);
    }

}