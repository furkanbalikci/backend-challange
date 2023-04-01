package com.furkanbalikci.simplecrudapp.controller;

import com.furkanbalikci.simplecrudapp.dto.EmployeeDTO;
import com.furkanbalikci.simplecrudapp.model.Employee;
import com.furkanbalikci.simplecrudapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<EmployeeDTO> employees = employeeService.getAll();
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesByCompanyName/{companyName}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByCompanyName(@PathVariable String companyName) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyName(companyName);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO.getCompanyName() == null || employeeDTO.getCompanyName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Company name cannot be empty !");
        }

        EmployeeDTO savedEmployee = employeeService.save(employeeDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Employee> employees) {
        List<Employee> savedEmployees = employeeService.saveAll(employees);
        if (savedEmployees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployees);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        if (employeeDTO.getCompanyName() == null || employeeDTO.getCompanyName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Company name cannot be empty !");
        }
        employeeService.update(employeeDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
