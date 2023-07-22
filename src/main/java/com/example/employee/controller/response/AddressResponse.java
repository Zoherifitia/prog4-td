package com.example.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressResponse {
    private Integer id;
    private String houseNumber;
    private String streetName;
    private String district;
    private String city;
    private String postalCode;
    private String region;
    private String country;
}
