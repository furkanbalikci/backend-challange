package com.furkanbalikci.simplecrudapp.controller;

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
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesByCompanyName/{companyName}")
    public ResponseEntity<List<Employee>> getEmployeesByCompanyName(@PathVariable String companyName) {
        List<Employee> employees = employeeService.getEmployeesByCompanyName(companyName);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee, @RequestParam String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Company name cannot be empty !");
        }

        Employee savedEmployee = employeeService.save(employee, companyName);
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
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Long id, @RequestParam String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Company name cannot be empty !");
        }

        employeeService.update(employee, id, companyName);
        Employee updatedEmployee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(employee);
        return ResponseEntity.ok().build();
    }
}
