package com.example.employee.repository;

import com.example.employee.modele.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findById(Integer id);

    /*@Query(value = "SELECT * FROM employee WHERE date_of_hire BETWEEN :arriveDate AND :departDate OR date_of_departure BETWEEN :arriveDate AND :departDate", nativeQuery = true)
    List<Employee> filterEmployeesByDateRange(Date arriveDate, Date departDate);*/
    @Query(value = "SELECT * FROM employee WHERE arrive_date = :date OR depart_date = :date", nativeQuery = true)
    List<Employee> filterEmployeesByDate(@Param("date") Date date);

}
