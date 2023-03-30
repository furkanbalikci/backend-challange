package com.furkanbalikci.simplecrudapp.service;

import com.furkanbalikci.simplecrudapp.exceptions.ResourceNotFoundException;
import com.furkanbalikci.simplecrudapp.model.Company;
import com.furkanbalikci.simplecrudapp.model.Employee;
import com.furkanbalikci.simplecrudapp.repository.CompanyRepository;
import com.furkanbalikci.simplecrudapp.repository.EmployeeRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public List<Employee> getAll() {
        try {
            return employeeRepository.findAll();
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee not exist with id: " + id)));

    }
    public List<Employee> getEmployeesByCompanyName(String companyName) {
        Optional<Company> company = companyRepository.findByName(companyName);
        if (company.isPresent()){
            return employeeRepository.findByCompanyId(company.get().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(("Company not exist with id: " + company.get().getId())));
        } else {
            throw new ResourceNotFoundException("Company not found with name = " + companyName);
        }

    }
    public List<Employee> saveAll(List<Employee> employees) {
        try {
            return employeeRepository.saveAll(employees);
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Employee save(Employee employee, String companyName) {
        Optional<Company> foundCompany = companyRepository.findByName(companyName);
        if (foundCompany.isPresent()) {
            employee.setCompanyId(foundCompany.get().getId());
        }else {
            throw new ResourceNotFoundException("Company not found with name : " + companyName);
        }
        return employeeRepository.save(employee);


    }

    public void update(Employee newEmployee, Long id, String companyName) {
        if (id == null || id == 0) {
            throw new NullPointerException();
        }

        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        Optional<Company> foundCompany = companyRepository.findByName(companyName);

        if (foundEmployee.isPresent()) {
            if (foundCompany.isPresent()) {
                employeeRepository.updateEmployeeById(
                        id, newEmployee.getName(), foundCompany.get().getId(),
                        newEmployee.getDepartment(), newEmployee.getAge(), newEmployee.getGender());
            }else {
                throw new ResourceNotFoundException("Company not found with name : " + companyName);
            }
        }else {
            throw new ResourceNotFoundException("Employee not found with id : " + id);
        }
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
