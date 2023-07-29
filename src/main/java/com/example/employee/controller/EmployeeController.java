package com.example.employee.controller;

import com.example.employee.controller.mapper.EmployeeMapper;
import com.example.employee.controller.response.CreateEmployeeResponse;
import com.example.employee.controller.response.EmployeeResponse;
import com.example.employee.controller.response.UpdateEmployeeResponse;
import com.example.employee.modele.Address;
import com.example.employee.modele.CIN;
import com.example.employee.modele.Email;
import com.example.employee.modele.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

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
                         @RequestParam(required = false) String sexStr,
                         @RequestParam(required = false) String function,
                         @RequestParam(required = false) String dateStr,
                         @RequestParam(required = false) String order,
                         Model model) throws ParseException {
        List<EmployeeResponse> employees;
        if (isEmpty(firstName, lastName, sexStr, function, dateStr)) {
            employees = employeeService.getEmployee().stream().map(employeeMapper::toRest).toList();
        } else {
            if (dateStr != null && !dateStr.isEmpty()) {
                //filtrez par date d'embauche ou de départ.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateStr);
                employees = employeeService.filterEmployeesByDateRange(date,order).stream().map(employeeMapper::toRest).toList();
            } else if (sexStr !=null && !sexStr.isEmpty()) {
                Employee.Sex sex = Employee.Sex.valueOf(sexStr);
                employees = employeeService.filterBySex(sex,order).stream().map(employeeMapper::toRest).toList();
            } else {
                // effectuez le filtrage des employés avec les autres paramètres fournis.
                employees = employeeService.filterEmployee(firstName, lastName, function,order).stream().map(employeeMapper::toRest).toList();
            }
        }

        model.addAttribute("employees", employees);
        return "employees";
    }

    private boolean isEmpty(String firstName, String lastName, String sexStr, String function, String dateStr) {
        return firstName == null && lastName == null && sexStr == null && function == null && dateStr == null;
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
            employee.setCin(new CIN());
        }
        if (employee.getAddress() == null) {
            employee.setAddress(new Address());
        }
        if (employee.getEmail() == null) {
            employee.setEmail(new Email());
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
        return "search";
    }

    @PostMapping("/employee/search")
    public String search(@ModelAttribute("employeeSearch") Employee employee, Model model) {
        Employee employeeResult = employeeService.getEmployeeById(employee.getId());
        model.addAttribute("employee", employeeResult);
        return "search"; // Renvoie la page de résultat de la recherche
    }

    @GetMapping("/employee/export")
    public void export(HttpServletResponse response,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String sexStr,
                       @RequestParam(required = false) String function,
                       @RequestParam(required = false) String dateStr,
                       @RequestParam(required = false) String order) throws Exception{
        response.setContentType("text/csv");
        String fileName= "employee.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey,headerValue);
        List<EmployeeResponse> employees;
        if (isEmpty(firstName, lastName, sexStr, function, dateStr)) {
            employees = employeeService.getEmployee().stream().map(employeeMapper::toRest).toList();
        } else {
            if (dateStr != null && !dateStr.isEmpty()) {
                //filtrez par date d'embauche ou de départ.
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateStr);
                employees = employeeService.filterEmployeesByDateRange(date,order).stream().map(employeeMapper::toRest).toList();
            } else if (sexStr !=null && !sexStr.isEmpty()) {
                Employee.Sex sex = Employee.Sex.valueOf(sexStr);
                employees = employeeService.filterBySex(sex,order).stream().map(employeeMapper::toRest).toList();
            } else {
                // effectuez le filtrage des employés avec les autres paramètres fournis.
                employees = employeeService.filterEmployee(firstName, lastName, function,order).stream().map(employeeMapper::toRest).toList();
            }
        }

        //List<EmployeeResponse> employeeResponseList= employeeService.getEmployee().stream().map(employeeMapper::toRest).toList();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Matricule", "First Name", "Last Name", "Sex", "Birth date","Function"};
        String[] nameMapping = {"matricule","firstName","lastName","sex","birthDate","function"};

        csvWriter.writeHeader(csvHeader);
        for (EmployeeResponse employee : employees){
            csvWriter.write(employee, nameMapping);
        }
        csvWriter.close();
    }
}
