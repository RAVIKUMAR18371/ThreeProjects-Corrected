package com.Employee.service.service;

import com.Employee.service.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO dto);
    EmployeeDTO update(Long id, EmployeeDTO dto);
    void delete(Long id);
    EmployeeDTO getSingle(Long id);
    List<EmployeeDTO> getAll();
 
    
}
