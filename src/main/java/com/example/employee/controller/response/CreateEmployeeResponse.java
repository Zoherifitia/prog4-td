package com.example.employee.controller.response;

import com.example.employee.modele.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CreateEmployeeResponse {
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Image image;
}
