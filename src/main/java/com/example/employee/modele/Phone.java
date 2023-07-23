package com.example.employee.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String phoneNumber;

}
