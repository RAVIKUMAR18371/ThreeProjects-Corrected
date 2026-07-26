package com.Employee.service.repository;

import java.util.*;

import com.Employee.service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Spring Data JPA method naming convention returning Optional
    Optional<Employee> findByEmpCodeAndCompanyName(String empCode, String companyName);
}