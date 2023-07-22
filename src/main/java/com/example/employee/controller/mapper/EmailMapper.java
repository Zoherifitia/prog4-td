package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmailResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {
    public Email toDomain(CreateEmployeeResponse createEmployeeResponse){
        Email email=new Email();
        email.setEmailPerso(createEmployeeResponse.getEmailPerso());
        email.setEmailPro(createEmployeeResponse.getEmailPro());
        return email;
    }
    public Email toDomain(UpdateEmployeeResponse updateEmployeeResponse){
        Email email=new Email();
        email.setEmailPerso(updateEmployeeResponse.getEmailPerso());
        email.setEmailPro(updateEmployeeResponse.getEmailPro());
        return email;
    }
}
