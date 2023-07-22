package com.example.employee.repository;

import com.example.employee.modele.CIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinRepository extends JpaRepository<CIN,Integer> {
}
