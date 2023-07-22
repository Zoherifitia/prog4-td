package com.example.employee.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String houseNumber;
    private String streetName;
    private String district;
    private String city;
    private String postalCode;
    private String region;
    private String country;
}
