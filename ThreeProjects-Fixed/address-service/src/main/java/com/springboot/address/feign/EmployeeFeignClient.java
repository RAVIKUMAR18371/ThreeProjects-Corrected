package com.springboot.address.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeFeignClient {

    @GetMapping("/employees/{id}")
    Object getEmployeeById(@PathVariable("id") Long id);

}