package com.springboot.address.dto;

import com.springboot.address.entity.AddressType;

import lombok.*;

/*
Why we need this specifically:
we need AddressDTO to specifically manage API responses.
Unlike the request DTO, this one includes 'empId' so that when the server replies,
the client can see exactly which employee the address was successfully linked to.
This separation ensures data consistency and security
 */
@Setter
@Getter
public class AddressDTO {
    private Long id;
    private Long empId;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private AddressType addressType;
}
