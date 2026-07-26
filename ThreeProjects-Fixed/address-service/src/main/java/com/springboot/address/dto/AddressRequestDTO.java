package com.springboot.address.dto;

import com.springboot.address.entity.AddressType;

public class AddressRequestDTO {
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private AddressType addressType;

    // Notice we do NOT include empId here
    // The client shouldn't send empId in the body because we get it from the URL path.

    // Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
        
    }
    public AddressType getAddressType() {
        return addressType;
    }


    
}
