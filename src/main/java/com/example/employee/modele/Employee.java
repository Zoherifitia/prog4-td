package com.example.employee.modele;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private byte[] image;

    private Sex sex;

    @OneToMany
    private List<Phone> phone;

    @OneToOne
    private Address address;

    @OneToOne
    private Email email;
    @OneToOne
    private CIN cin;
    private String function;
    private Integer numberOfChildren;
    private LocalDate arriveDate;

    @Column(nullable = true)
    private LocalDate departDate;
    private CategorieSocioProfessionnelle categorieSocioProfessionnelle;
    private String cnaps;

    public enum Sex{
        M,F
    }
    public enum CategorieSocioProfessionnelle{
        manager,employe,worker,technician,unskilled
    }

    public String generateMatricule() {
      String mat = String.format("%05d", id);
      this.matricule= "STD"+mat;
      return matricule;
    }
}
