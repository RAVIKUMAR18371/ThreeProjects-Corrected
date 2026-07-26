package com.Employee.service.service;

import com.Employee.service.entity.Employee;
import com.Employee.service.dto.EmployeeDTO;
import com.Employee.service.repository.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        if (dto.getId() != null) {
            throw new RuntimeException("Employee already exists with this ID");
        }
        Employee entity = modelMapper.map(dto, Employee.class);
        Employee savedEntity = repository.save(entity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto){
        if (!id.equals(dto.getId())) {
            throw new RuntimeException("ID in URL does not match ID in payload");
        }
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(dto.getName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setEmpCode(dto.getEmpCode());
        existingEmployee.setCompanyName(dto.getCompanyName());

        Employee updatedEntity = repository.save(existingEmployee);
        return modelMapper.map(updatedEntity, EmployeeDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        repository.deleteById(id);
    }

    @Override
    public EmployeeDTO getSingle(Long id) {
        Employee entity = repository.findById(id)
                                    .orElseThrow(()-> new RuntimeException("Employee not found"));
        return modelMapper.map(entity, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return repository.findAll().stream()
                                    .map(entity -> modelMapper.map(entity, EmployeeDTO.class))
                                    .collect(Collectors.toList());
    }
}