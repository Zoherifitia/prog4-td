package com.example.employee.controller.response;

import com.example.employee.modele.CIN;
import com.example.employee.modele.Email;
import com.example.employee.modele.Employee;
import com.example.employee.modele.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateEmployeeResponse {
    private Integer id;
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private byte[] image;
    private Employee.Sex sex;
    private List<Phone> phone;
    private String adress;
    private Email email;
    private String cinNumber;
    private LocalDate cinDateDelivery;
    private String cinPlaceDelivery;
    private String fonction;
    private Integer numberOfChildren;
    private LocalDate arriveDate;
    private LocalDate departDate;
    private Employee.CategorieSocioProfessionnelle categorieSocioProfessionnelle;
    private String cnaps;
}
