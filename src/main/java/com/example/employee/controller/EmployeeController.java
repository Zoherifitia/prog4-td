package com.example.employee.controller;

import com.example.employee.controller.mapper.EmployeeMapper;
import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Employee;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public String updateEmployee(@ModelAttribute("createEmployee") CreateEmployeeResponse createEmployeeResponse,
                                 @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        if (!photoFile.isEmpty()) {
            // Récupérer les données binaires de l'image
            byte[] imageBytes = photoFile.getBytes();
            // Enregistrer les données binaires de l'image dans l'objet createEmployeeResponse
            createEmployeeResponse.setImage(imageBytes);
        }else {
            System.out.println("aucune image");
        }
        employeeService.saveEmployee(employeeMapper.toDomain(createEmployeeResponse));
        return "redirect:/save-employee";
    }
    @GetMapping("/employee/{id}/image")
    public ResponseEntity<byte[]> getEmployeeImage(@PathVariable("id") Integer id) {
        // Récupérer les données binaires de l'image à partir de l'employé avec l'identifiant donné (id)
        Employee employee = employeeService.getEmployeeById(id);
        byte[] imageBytes = employee.getImage();

        // Créer une ResponseEntity avec les données de l'image et les en-têtes appropriés
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Spécifier le type de contenu de l'image
        headers.setContentLength(imageBytes.length); // Spécifier la longueur des données de l'image

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeDetails(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "ficheEmployee";
    }
    @GetMapping("/employee/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("updateEmployee", employee);
        return "updateEmployee";
    }
    @PostMapping("/employee/{id}/edit")
    public String submitEditForm(@ModelAttribute("updateEmployee") UpdateEmployeeResponse updateEmployeeResponse,
                                 @RequestParam("photoFile") MultipartFile photoFile) throws IOException{
        if(!photoFile.isEmpty()){
            byte[] imageBytes = photoFile.getBytes();
            updateEmployeeResponse.setImage(imageBytes);
        }else {
            System.out.println("aucune image");
        }
        employeeService.updateEmployee(employeeMapper.toDomain(updateEmployeeResponse));
        return "redirect:/employees";
    }
}
