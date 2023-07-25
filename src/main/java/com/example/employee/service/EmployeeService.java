package com.example.employee.service;

import com.example.employee.modele.*;
import com.example.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /*public List<Employee> filterEmployees(String firstName, String lastName, Employee.Sex sex, String function) {
        return employeeRepository.findByFirstNameAndLastNameAndSexAndFunction(firstName, lastName, sex, function);
    }*/
    public List<Employee> filterEmployees(String firstName, String lastName, Employee.Sex sex, String function) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        // Créer les prédicats pour les filtres
        Predicate predicate = cb.conjunction(); // Combinaison de tous les prédicats (AND)

        if (firstName != null && !firstName.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("firstName"), firstName));
        }

        if (lastName != null && !lastName.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("lastName"), lastName));
        }

        if (sex != null) {
            predicate = cb.and(predicate, cb.equal(root.get("sex"), sex));
        }

        if (function != null && !function.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("function"), function));
        }

        cq.where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }

}


