package com.example.employee.repository;

import com.example.employee.modele.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findById(Integer id);

    List<Employee> findByFirstNameAndLastNameAndSexAndFunction(String firstName, String lastName, Employee.Sex sex, String function);
}
