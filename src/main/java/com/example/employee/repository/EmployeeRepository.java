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
    @Query(value = "SELECT * FROM employee WHERE arrive_date = :date OR depart_date = :date"
            , nativeQuery = true)
    List<Employee> filterEmployeesByDate(@Param("date") Date date);

    //triage avec date
    @Query(value = "SELECT * FROM employee " +
            "WHERE UPPER(first_name) LIKE CONCAT('%',UPPER(:firstName),'%')" +
            "AND UPPER(last_name) LIKE CONCAT('%',UPPER(:lastName),'%')" +
            "AND UPPER(function) LIKE CONCAT('%',UPPER(:function),'%')", nativeQuery = true)
    List<Employee> filterEmployees(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("function") String function);
    @Query(value = "SELECT * FROM employee WHERE sex = :sex", nativeQuery = true)
    List<Employee> filterEmployeeBySex(@Param("sex")Employee.Sex sex);

    @Query(value = "SELECT * FROM employee WHERE sex = :sex"+"ORDER BY CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN sex " +
            "  WHEN UPPER(:order) = 'DESC' THEN sex " +
            "END ASC, ", nativeQuery = true)
    List<Employee> filterEmployeesOrderBySexAsc(@Param("sex")Employee.Sex sex,String order );
    @Query(value = "SELECT * FROM employee WHERE sex = :sex"+"ORDER BY CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN sex " +
            "  WHEN UPPER(:order) = 'DESC' THEN sex " +
            "END ASC, ", nativeQuery = true)
    List<Employee> filterEmployeesOrderBySexDesc(@Param("sex")Employee.Sex sex,String order);

    //triage
    @Query(value = "SELECT * FROM employee " +
            "WHERE UPPER(first_name) LIKE CONCAT('%',UPPER(:firstName),'%')" +
            "AND UPPER(last_name) LIKE CONCAT('%',UPPER(:lastName),'%')" +
            "AND UPPER(function) LIKE CONCAT('%',UPPER(:function),'%')"+"ORDER BY CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN first_name " +
            "  WHEN UPPER(:order) = 'DESC' THEN first_name " +
            "END ASC, " +
            "CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN last_name " +
            "  WHEN UPPER(:order) = 'DESC' THEN last_name " +
            "END ASC, " +
            "CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN function " +
            "  WHEN UPPER(:order) = 'DESC' THEN function " +
            "END ASC", nativeQuery = true)
    List<Employee> filterEmployeesOrderByFieldAsc(@Param("firstName") String firstName,
                                                  @Param("lastName") String lastName,
                                                  @Param("function") String function,
                                                  @Param("order") String order);
    @Query(value = "SELECT * FROM employee " +
            "WHERE UPPER(first_name) LIKE CONCAT('%',UPPER(:firstName),'%')" +
            "AND UPPER(last_name) LIKE CONCAT('%',UPPER(:lastName),'%')" +
            "AND UPPER(function) LIKE CONCAT('%',UPPER(:function),'%')"+"ORDER BY CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN first_name " +
            "  WHEN UPPER(:order) = 'DESC' THEN first_name " +
            "END DESC, " +
            "CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN last_name " +
            "  WHEN UPPER(:order) = 'DESC' THEN last_name " +
            "END DESC, " +
            "CASE " +
            "  WHEN UPPER(:order) = 'ASC' THEN function " +
            "  WHEN UPPER(:order) = 'DESC' THEN function " +
            "END DESC", nativeQuery = true)
    List<Employee> filterEmployeesOrderByFieldDesc(@Param("firstName") String firstName,
                                                   @Param("lastName") String lastName,
                                                   @Param("function") String function,
                                                   @Param("order") String order);
}
