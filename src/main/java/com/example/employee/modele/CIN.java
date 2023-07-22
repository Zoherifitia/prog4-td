package com.example.employee.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CIN {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String cinNumber;
    private LocalDate dateDelivery;
    private String placeDelivery;
}
