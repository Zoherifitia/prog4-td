package com.example.employee.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailResponse {
    private Integer id;
    private String emailPro;
    private String emailPerso;
}
