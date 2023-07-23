package com.example.employee.service;

import com.example.employee.modele.*;
import com.example.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private CinService cinService;
    private EmailService emailService;
    private AddressService addressService;
    private PhoneService phoneService;
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee){
        CIN cin = cinService.save(employee.getCin());
        employee.setCin(cin);
        Email email =emailService.save(employee.getEmail());
        employee.setEmail(email);
        Address address= addressService.save(employee.getAddress());
        employee.setAddress(address);
        List<Phone> phoneList = employee.getPhone();
        List<Phone> savedPhoneList = new ArrayList<>();
        for (Phone phone : phoneList) {
            Phone savedPhone = phoneService.save(phone);
            savedPhoneList.add(savedPhone);
        }
        employee.setPhone(savedPhoneList);
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
