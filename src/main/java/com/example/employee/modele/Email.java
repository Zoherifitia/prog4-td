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
public class Email {
    @Id
    private Integer id;
    private String emailPro;
    private String emailPerso;
}
