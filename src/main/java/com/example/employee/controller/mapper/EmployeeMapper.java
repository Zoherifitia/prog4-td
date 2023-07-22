package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private CinMapper cinMapper;
    private EmailMapper emailMapper;
    public EmployeeResponse toRest(Employee domain){
        return EmployeeResponse.builder()
                .id(domain.getId())
                .matricule(domain.generateMatricule())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .birthDate(domain.getBirthDate())
                .image(domain.getImage())
                .sex(domain.getSex())
                .email(domain.getEmail())
                .adress(domain.getAdress())
                .phone(domain.getPhone())
                .cin(domain.getCin())
                .function(domain.getFunction())
                .numberOfChildren(domain.getNumberOfChildren())
                .arriveDate(domain.getArriveDate())
                .departDate(domain.getDepartDate())
                .categorieSocioProfessionnelle(domain.getCategorieSocioProfessionnelle())
                .cnaps(domain.getCnaps())
                .build();
    }

    public Employee toDomain(CreateEmployeeResponse employee){
        Employee employee1=new Employee();
        employee1.setMatricule(employee.getMatricule());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setBirthDate(employee.getBirthDate());
        employee1.setImage(employee.getImage());
        employee1.setSex(employee.getSex());
        employee1.setEmail(emailMapper.toDomain(employee));
        employee1.setAdress(employee.getAdress());
        employee1.setPhone(employee.getPhone());
        employee1.setCin(cinMapper.toDomain(employee));
        employee1.setFunction(employee.getFunction());
        employee1.setNumberOfChildren(employee.getNumberOfChildren());
        employee1.setArriveDate(employee.getArriveDate());
        employee1.setDepartDate(employee.getDepartDate());
        employee1.setCategorieSocioProfessionnelle(employee.getCategorieSocioProfessionnelle());
        employee1.setCnaps(employee.getCnaps());
        return employee1;
    }
    public Employee toDomain(UpdateEmployeeResponse employee){
        Employee employee2= new Employee();
        employee2.setId(employee.getId());
        employee2.setFirstName(employee.getFirstName());
        employee2.setLastName(employee.getLastName());
        employee2.setMatricule(employee.getMatricule());
        employee2.setBirthDate(employee.getBirthDate());
        employee2.setImage(employee.getImage());
        employee2.setSex(employee.getSex());
        employee2.setEmail(emailMapper.toDomain(employee));
        employee2.setAdress(employee.getAdress());
        employee2.setPhone(employee.getPhone());
        employee2.setCin(cinMapper.toDomain(employee));
        employee2.setFunction(employee.getFunction());
        employee2.setNumberOfChildren(employee.getNumberOfChildren());
        employee2.setArriveDate(employee.getArriveDate());
        employee2.setDepartDate(employee.getDepartDate());
        employee2.setCategorieSocioProfessionnelle(employee.getCategorieSocioProfessionnelle());
        employee2.setCnaps(employee.getCnaps());
        return employee2;
    }
}
