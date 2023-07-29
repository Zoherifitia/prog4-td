package com.example.employee.service;

import com.example.employee.modele.*;
import com.example.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private CinService cinService;
    private EmailService emailService;
    private AddressService addressService;
    private PhoneService phoneService;
    private EntityManager entityManager;
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

    public List<Employee> filterEmployee(String firstName, String lastName, String function,String order) {
        //order
        List<Employee> employees = employeeRepository.filterEmployees(firstName, lastName, function);

        if (order != null && !order.isEmpty()) {
            // Trier la liste des employés en fonction de l'ordre choisi.
            if (order.equals("asc")) {
               employees= employeeRepository.filterEmployeesOrderByFieldAsc(firstName,lastName,function,order);
            } else if (order.equals("desc")) {
                employees=employeeRepository.filterEmployeesOrderByFieldDesc(firstName,lastName,function,order);
            }
        }
        return employees;
    }

    public List<Employee> filterEmployeesByDateRange(Date date,String order) {
        //order
        List<Employee> employees = employeeRepository.filterEmployeesByDate(date);
        return employees;
    }
    public List<Employee> filterBySex(Employee.Sex sex,String order){
        //order
        List<Employee> employees = employeeRepository.filterEmployeeBySex(sex);
        if (order != null && !order.isEmpty()) {
            if (order.equals("asc")) {
                employees= employeeRepository.filterEmployeesOrderBySexAsc(sex,order);
            } else if (order.equals("desc")) {
                employees=employeeRepository.filterEmployeesOrderBySexDesc(sex,order);
            }
        }
        return employees;
    }
}


