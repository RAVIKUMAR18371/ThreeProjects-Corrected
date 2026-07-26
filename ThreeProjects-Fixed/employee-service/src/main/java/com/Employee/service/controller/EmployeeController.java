package com.Employee.service.controller;

import com.Employee.service.dto.EmployeeDTO;
import com.Employee.service.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * WHY WE NEED THIS:
 * EmployeeService/EmployeeServiceImpl already implemented save/update/delete/
 * getSingle/getAll, but there was no controller anywhere in the project to
 * expose them over HTTP - the endpoints simply didn't exist. This wires the
 * existing service methods up to REST endpoints, mirroring the style used in
 * AddressController.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO dto) {
        return employeeService.save(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        return employeeService.getSingle(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll();
    }
}
