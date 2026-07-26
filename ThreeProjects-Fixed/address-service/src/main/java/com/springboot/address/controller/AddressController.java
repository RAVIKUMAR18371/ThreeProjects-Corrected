package com.springboot.address.controller;

import com.springboot.address.dto.AddressDTO;
import com.springboot.address.dto.AddressRequestDTO;
import com.springboot.address.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * WHY WE NEED THIS:
 * The AddressController is needed to expose the endpoints to accept API requests [1].
 * We use a separate port for this service since it's an independent microservice.
 */
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Exposes POST endpoint to accept address requests [1]
    @PostMapping("/save/{empId}")
    public List<AddressDTO> saveAddresses(@PathVariable Long empId, @RequestBody List<AddressRequestDTO> requestDtos) {
        return addressService.saveAddresses(empId, requestDtos);
    }

    @PutMapping("/update/{empId}")
    public List<AddressDTO> updateAddresses(@PathVariable Long empId, @RequestBody List<AddressRequestDTO> requestDtos) {
        return addressService.updateAddresses(empId, requestDtos);
    }
}