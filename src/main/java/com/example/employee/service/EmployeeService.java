package com.example.employee.service;

import com.example.employee.modele.CIN;
import com.example.employee.modele.Email;
import com.example.employee.modele.Employee;
import com.example.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private CinService cinService;
    private EmailService emailService;
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee){
        CIN cin = cinService.save(employee.getCin());
        employee.setCin(cin);
        Email email =emailService.save(employee.getEmail());
        employee.setEmail(email);
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("L'employé avec l'ID " + id + " n'a pas été trouvé."));
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
