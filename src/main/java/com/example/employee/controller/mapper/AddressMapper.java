package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toDomain(CreateEmployeeResponse createEmployeeResponse){
        Address address=new Address();
        address.setHouseNumber(createEmployeeResponse.getHouseNumber());
        address.setStreetName(createEmployeeResponse.getStreetName());
        address.setDistrict(createEmployeeResponse.getDistrict());
        address.setCity(createEmployeeResponse.getCity());
        address.setPostalCode(createEmployeeResponse.getPostalCode());
        address.setRegion(createEmployeeResponse.getRegion());
        address.setCountry(createEmployeeResponse.getCountry());
        return address;
    }
    public Address toDomain(UpdateEmployeeResponse updateEmployeeResponse){
        Address address=new Address();
        address.setHouseNumber(updateEmployeeResponse.getHouseNumber());
        address.setStreetName(updateEmployeeResponse.getStreetName());
        address.setDistrict(updateEmployeeResponse.getDistrict());
        address.setCity(updateEmployeeResponse.getCity());
        address.setPostalCode(updateEmployeeResponse.getPostalCode());
        address.setRegion(updateEmployeeResponse.getRegion());
        address.setCountry(updateEmployeeResponse.getCountry());
        return address;
    }
}
