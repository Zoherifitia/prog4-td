package com.example.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CinResponse {
    private Integer id;
    private String cinNumber;
    private LocalDate dateDelivery;
    private String placeDelivery;
}
