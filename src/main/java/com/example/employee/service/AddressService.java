package com.example.employee.service;

import com.example.employee.modele.Address;
import com.example.employee.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;
    public Address save(Address address){
        return addressRepository.save(address);
    }
    public Address update(Address address){
        return addressRepository.save(address);
    }
}
