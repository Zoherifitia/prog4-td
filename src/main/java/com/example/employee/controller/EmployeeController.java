package com.example.employee.controller;

import com.example.employee.controller.mapper.EmployeeMapper;
import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.modele.Employee;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @GetMapping("/employees")
    public String getAll(Model model){
        List<EmployeeResponse> employees = employeeService.getEmployee().stream().map(employeeMapper::toRest).toList();
        model.addAttribute("employees",employees);
        return "employees";
    }
    @GetMapping("/save-employee")
    public String getEmployee(Model model){
        CreateEmployeeResponse createEmployeeResponse = new CreateEmployeeResponse();
        model.addAttribute("createEmployee", createEmployeeResponse);
        return "createEmployee";
    }
    @PostMapping("/save-employee")
    public String updateEmployee(@ModelAttribute("createEmployee") CreateEmployeeResponse createEmployeeResponse){
        employeeService.saveEmployee(employeeMapper.toDomain(createEmployeeResponse));
        return "redirect:/save-employee";
    }
}
