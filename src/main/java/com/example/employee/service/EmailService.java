package com.example.employee.service;

import com.example.employee.modele.Email;
import com.example.employee.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository emailRepository;
    public Email save(Email email){
        return emailRepository.save(email);
    }
    public Email update(Email email){
        return emailRepository.save(email);
    }
}
