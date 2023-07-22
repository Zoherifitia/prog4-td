package com.example.employee.controller.mapper;

import com.example.employee.controller.response.CinResponse;
import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.CIN;
import org.springframework.stereotype.Component;

@Component
public class CinMapper {
    public CIN toDomain(CreateEmployeeResponse createEmployeeResponse){
        CIN cin1= new CIN();
        cin1.setCinNumber(createEmployeeResponse.getCinNumber());
        cin1.setDateDelivery(createEmployeeResponse.getCinDateDelivery());
        cin1.setPlaceDelivery(createEmployeeResponse.getCinPlaceDelivery());
        return cin1;
    }
    public  CIN toDomain(UpdateEmployeeResponse cin){
        CIN cin2=new CIN();
        cin2.setCinNumber(cin.getCinNumber());
        cin2.setDateDelivery(cin.getCinDateDelivery());
        cin2.setPlaceDelivery(cin.getCinPlaceDelivery());
        return cin2;
    }
}
