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
    @Query(value = "SELECT * FROM employee WHERE arrive_date = :date OR depart_date = :date", nativeQuery = true)
    List<Employee> filterEmployeesByDate(@Param("date") Date date);

    /*@Query(value = "SELECT * FROM employee " +
            "WHERE UPPER(first_name) LIKE CONCAT('%',UPPER(first_name),'%')" +
            "AND UPPER(last_name) LIKE CONCAT('%',UPPER(last_name),'%')" +
            "AND UPPER(sex) LIKE CONCAT('%',UPPER(sex),'%')" +
            "AND UPPER(function) LIKE CONCAT('%',UPPER(function),'%')", nativeQuery = true)
    List<Employee> filterEmployees(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("sex") Employee.Sex sex,
                                   @Param("function") String function);*/
    @Query(value = "SELECT * FROM employee " +
            "WHERE UPPER(first_name) LIKE CONCAT('%',UPPER(:firstName),'%')" +
            "AND UPPER(last_name) LIKE CONCAT('%',UPPER(:lastName),'%')" +
            "AND UPPER(function) LIKE CONCAT('%',UPPER(:function),'%')", nativeQuery = true)
    List<Employee> filterEmployees(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("function") String function);
    @Query(value = "SELECT * FROM employee WHERE sex = :sex", nativeQuery = true)
    List<Employee> filterEmployeeBySex(@Param("sex")Employee.Sex sex);

}
