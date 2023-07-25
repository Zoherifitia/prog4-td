package com.example.employee.controller;

import com.example.employee.controller.mapper.EmployeeMapper;
import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @GetMapping("/employees")
    public String getAll(@RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName,
                         @RequestParam(required = false) Employee.Sex sex,
                         @RequestParam(required = false) String function,
                         @RequestParam(required = false) String dateStr,
                         /*@RequestParam(required = false) String departDateStr,*/
                         Model model) throws ParseException {
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        if (dateStr != null && !dateStr.isEmpty()) {
            date = dateFormat.parse(dateStr);
        }*/
        List<EmployeeResponse> employees;
        if (isEmpty(firstName, lastName, sex, function, dateStr)) {
            // Si tous les paramètres de filtrage sont vides, récupérez tous les employés.
            employees = employeeService.getEmployee().stream().map(employeeMapper::toRest).toList();
        } else {
            if (dateStr != null && !dateStr.isEmpty()) {
                // Si dateStr est spécifié, filtrez par date d'embauche ou de départ.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateStr);
                employees = employeeService.filterEmployeesByDateRange(date).stream().map(employeeMapper::toRest).toList();
            } else {
                // Sinon, effectuez le filtrage des employés avec les autres paramètres fournis.
                employees = employeeService.filterEmployees(firstName, lastName, sex, function).stream().map(employeeMapper::toRest).toList();
            }
        }
        model.addAttribute("employees", employees);
        return "employees";
    }

    //vérifier si tous les paramètres de filtrage sont vides
    private boolean isEmpty(String firstName, String lastName, Employee.Sex sex, String function, String dateStr) {
        return firstName == null && lastName == null && sex == null && function == null && dateStr == null;
    }

    @GetMapping("/save-employee")
    public String getEmployee(Model model){
        CreateEmployeeResponse createEmployeeResponse = new CreateEmployeeResponse();
        model.addAttribute("createEmployee", createEmployeeResponse);
        return "createEmployee";
    }
    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("createEmployee") CreateEmployeeResponse createEmployeeResponse,
                                 @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        if (!photoFile.isEmpty()) {
            // Récupérer les données binaires de l'image
            byte[] imageBytes = photoFile.getBytes();
            // Enregistrer les données binaires de l'image dans l'objet createEmployeeResponse
            createEmployeeResponse.setImage(imageBytes);
        }else {
            System.out.println("aucune image");
        }
        List<String> formattedPhones = Arrays.stream(createEmployeeResponse.getPhone().split(","))
                .map(phone -> "-" + phone)
                .collect(Collectors.toList());
        createEmployeeResponse.setPhone(String.join("<br/>", formattedPhones));

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
        if (employee.getCin() == null) {
            employee.setCin(new CIN()); // Créer un nouvel objet Cin s'il est nul
        }
        if (employee.getAddress() == null) {
            employee.setAddress(new Address()); // Créer un nouvel objet Address s'il est nul
        }
        if (employee.getEmail() == null) {
            employee.setEmail(new Email()); // Créer un nouvel objet Email s'il est nul
        }
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


    //rechercher un employee
    @GetMapping("/employee/search")
    public String showSearchForm(Model model) {
        model.addAttribute("employeeSearch", new Employee()); // Créez une nouvelle instance d'Employee pour le formulaire
        return "search"; // Renvoie la page du formulaire de recherche
    }

    @PostMapping("/employee/search")
    public String search(@ModelAttribute("employeeSearch") Employee employee, Model model) {
        Employee employeeResult = employeeService.getEmployeeById(employee.getId());
        model.addAttribute("employee", employeeResult);
        return "search"; // Renvoie la page de résultat de la recherche
    }

    //filtre
    /*@GetMapping("/employee/filter")
    public String showFilterForm(Model model) {
        model.addAttribute("employeeFilter", new EmployeeFilter());
        return "filter";
    }

    @PostMapping("/employee/filter")
    public String filter(@ModelAttribute("employeeFilter") EmployeeFilter employeeFilter, Model model) {
        List<Employee> filteredEmployees = employeeService.getEmployeesByFilter(employeeFilter);
        model.addAttribute("employees", filteredEmployees);
        return "filter";
    }*/
}
