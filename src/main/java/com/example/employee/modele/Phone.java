package com.example.employee.modele;

import jakarta.persistence.Entity;
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
    private Integer id;
    private String number;
}
