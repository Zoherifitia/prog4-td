package com.example.employee.service;

import com.example.employee.modele.CIN;
import com.example.employee.repository.CinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CinService {
    private CinRepository repository;
    public CIN save(CIN cin){
        return repository.save(cin);
    }
    public CIN update(CIN cin){
        return repository.save(cin);
    }
}
