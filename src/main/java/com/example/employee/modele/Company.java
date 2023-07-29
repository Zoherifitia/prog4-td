package com.example.employee.modele;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "entreprise")
public class Company {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String description;
        private String slogan;
        private String address;
        private String email;
        private String phones;
        private String nif;
        private String stat;
        private String rcs;
        private String logo;
        public List<String> getTelephonesList() {
                return Arrays.asList(phones.split(","));
        }
}
