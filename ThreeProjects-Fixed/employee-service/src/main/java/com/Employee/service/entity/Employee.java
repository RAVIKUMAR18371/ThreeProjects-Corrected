package com.Employee.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String empCode;
    private String companyName;

    // Default Constructor
    public Employee() {
    }

    // Parameterized Constructor
    public Employee(Long id, String name, String email, String empCode, String companyName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.empCode = empCode;
        this.companyName = companyName;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for empCode
    public String getEmpCode() {
        return empCode;
    }

    // Setter for empCode
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    // Getter for companyName
    public String getCompanyName() {
        return companyName;
    }

    // Setter for companyName
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", empCode='" + empCode + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}