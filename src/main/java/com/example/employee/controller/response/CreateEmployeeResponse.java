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
@Builder
@Data
@NoArgsConstructor
public class CreateEmployeeResponse {
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private byte[] image;
    private Employee.Sex sex;
    private String phone;
    private String houseNumber;
    private String streetName;
    private String district;
    private String city;
    private String postalCode;
    private String region;
    private String country;
    private String emailPro;
    private String emailPerso;
    private String cinNumber;
    private LocalDate cinDateDelivery;
    private String cinPlaceDelivery;
    private String function;
    private Integer numberOfChildren;
    private LocalDate arriveDate;
    private LocalDate departDate;
    private Employee.CategorieSocioProfessionnelle categorieSocioProfessionnelle;
    private String cnaps;
}
