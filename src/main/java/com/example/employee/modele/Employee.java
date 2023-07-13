package com.example.employee.modele;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String matricule;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @OneToOne
    private Image image;

    public String generateMatricule() {
      String mat = String.format("%05d", id);
      this.matricule= "STD"+mat;
      return matricule;
    }


}
