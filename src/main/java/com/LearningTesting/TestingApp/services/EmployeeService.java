package com.LearningTesting.TestingApp.services;

import com.LearningTesting.TestingApp.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
