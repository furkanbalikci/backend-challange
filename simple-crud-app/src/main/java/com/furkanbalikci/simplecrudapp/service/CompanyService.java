package com.furkanbalikci.simplecrudapp.service;

import com.furkanbalikci.simplecrudapp.exceptions.EntityAlreadyExistException;
import com.furkanbalikci.simplecrudapp.exceptions.ResourceNotFoundException;
import com.furkanbalikci.simplecrudapp.model.Company;
import com.furkanbalikci.simplecrudapp.repository.CompanyRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getAll() {
        try {
            return companyRepository.findAll();
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Company not exist with id: " + id)));
    }

    public Company save(Company company) {
        Optional<Company> foundCompany = companyRepository.findByName(company.getName());
        if (foundCompany.isPresent()) {
            throw new EntityAlreadyExistException("Company already exist with name = " + company.getName());
        } else {
            return companyRepository.save(company);
        }

    }

    public void update(Company newCompany, Long id) {
        if (id == null || id == 0) {
            throw new NullPointerException();
        }

        Optional<Company> foundCompany = companyRepository.findById(id);

        if (foundCompany.isPresent()) {
             companyRepository.updateCompanyById(
                    id, newCompany.getName(), newCompany.getNumberOfEmployees(), newCompany.getIndustries());
        }else {
            throw new ResourceNotFoundException("Company not found with id = " + id);
        }

    }

    public void delete (Company company) {
        companyRepository.delete(company);
    }

    public List<Company> saveAll(List<Company> companies) {
        try {
            return companyRepository.saveAll(companies);
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
