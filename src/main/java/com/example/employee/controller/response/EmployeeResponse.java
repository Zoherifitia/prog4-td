package com.example.employee.controller.response;

import com.example.employee.modele.*;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private Integer id;
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private byte[] image;
    private Employee.Sex sex;
    private List<Phone> phone;
    private Address address;
    private Email email;
    private CIN cin;
    private String function;
    private Integer numberOfChildren;
    private LocalDate arriveDate;
    private LocalDate departDate;
    private Employee.CategorieSocioProfessionnelle categorieSocioProfessionnelle;
    private String cnaps;
}
