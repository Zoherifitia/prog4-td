package com.example.employee.controller.response;

import com.example.employee.modele.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Image image;
}
