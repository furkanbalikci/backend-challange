package com.furkanbalikci.simplecrudapp.controller;

import com.furkanbalikci.simplecrudapp.exceptions.EntityAlreadyExistException;
import com.furkanbalikci.simplecrudapp.exceptions.ResourceNotFoundException;
import com.furkanbalikci.simplecrudapp.model.Company;
import com.furkanbalikci.simplecrudapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companies = companyService.getAll();
        if (companies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @GetMapping("/getCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PostMapping("/save")
    public ResponseEntity<Company> save(@RequestBody Company company) {
        try {
            Company savedCompany = companyService.save(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<Company>> saveAll(@RequestBody List<Company> companies) {
        List<Company> savedCompanies = companyService.saveAll(companies);
        if (savedCompanies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompanies);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id) {
        companyService.update(company,id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        companyService.delete(company);
        return ResponseEntity.ok().build();
    }


}
