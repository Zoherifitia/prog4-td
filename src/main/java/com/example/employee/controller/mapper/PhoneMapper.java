package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneMapper {

    public List<Phone> toDomain(CreateEmployeeResponse createEmployeeResponse){
        Phone phone=new Phone();
        phone.setPhoneNumber(createEmployeeResponse.getPhone());
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        return phoneList;
    }
    public List<Phone> toDomain(UpdateEmployeeResponse updateEmployeeResponse){
        Phone phone=new Phone();
        phone.setPhoneNumber(updateEmployeeResponse.getPhoneNumber());
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        return phoneList;
    }

}
