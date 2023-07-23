package com.example.employee.service;

import com.example.employee.modele.Phone;
import com.example.employee.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {
    private PhoneRepository phoneRepository;
    public Phone save(Phone phone){
        return phoneRepository.save(phone);
    }
    public Phone update(Phone phone){
        return phoneRepository.save(phone);
    }
}
