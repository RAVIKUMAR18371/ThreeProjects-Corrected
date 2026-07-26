package com.Employee.service.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * WHY WE NEED THIS:
 * EmployeeServiceImpl takes a ModelMapper in its constructor to convert between
 * Employee entities and EmployeeDTOs. This file existed but was completely empty
 * in the original project, so Spring had no ModelMapper bean to inject and the
 * app would fail to start with "No qualifying bean of type ModelMapper".
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PUBLIC);
        return modelMapper;
    }
}
