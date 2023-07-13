package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.modele.Employee;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmployeeMapper {
    public EmployeeResponse toRest(Employee domain){
        return EmployeeResponse.builder()
                .matricule(domain.generateMatricule())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .birthDate(domain.getBirthDate())
                //.image(domain.getImage())
                .build();
    }

    public Employee toDomain(CreateEmployeeResponse employee){
        Employee employee1=new Employee();
        employee1.setMatricule(employee.getMatricule());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setBirthDate(employee.getBirthDate());
       //. employee1.setImage(employee.getImage());
        return employee1;
    }
}
