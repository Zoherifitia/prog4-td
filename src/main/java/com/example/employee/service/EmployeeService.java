package com.example.employee.service;

import com.example.employee.modele.Employee;
import com.example.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
