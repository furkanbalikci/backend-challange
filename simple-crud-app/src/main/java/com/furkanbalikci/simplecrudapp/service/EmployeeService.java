package com.furkanbalikci.simplecrudapp.service;

import com.furkanbalikci.simplecrudapp.dto.EmployeeDTO;
import com.furkanbalikci.simplecrudapp.exceptions.EntityAlreadyExistException;
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

    public List<EmployeeDTO> getAll() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return EmployeeDTO.toListDTO(employees);
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee not exist with id: " + id)));
        return EmployeeDTO.toDTO(employee);

    }
    public List<EmployeeDTO> getEmployeesByCompanyName(String companyName) {
        Optional<Company> company = companyRepository.findByName(companyName);
        if (company.isPresent()){
            List<Employee> employees = employeeRepository.findByCompanyId(company.get().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(("Company not exist with id: " + company.get().getId())));
            return EmployeeDTO.toListDTO(employees);

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

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Optional<Employee> foundEmployee = employeeRepository.findByNameAndCompanyName(employeeDTO.getName(),employeeDTO.getCompanyName());
        if (foundEmployee.isPresent()) {
            throw new EntityAlreadyExistException("Employee already exist with email = " + employeeDTO);
        } else {
            Optional<Company> company = companyRepository.findByName(employeeDTO.getCompanyName());
            Employee employee = EmployeeDTO.toEntity(employeeDTO,company.get());
            System.out.println("Employeee : " + employee);
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeDTO.toDTO(savedEmployee);
        }
    }


    public void update(EmployeeDTO newEmployeeDTO, Long id) {
        if (id == null || id == 0) {
            throw new NullPointerException();
        }

        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        if (foundEmployee.isPresent()) {
            Optional<Company> company = companyRepository.findByName(newEmployeeDTO.getCompanyName());
            Employee newEmployee = EmployeeDTO.toEntity(newEmployeeDTO,company.get());
            newEmployee.setId(id);
            employeeRepository.updateEmployeeById(
                    newEmployee.getId(), newEmployee.getName(),newEmployee.getCompany(),newEmployee.getDepartment(),
                    newEmployee.getAge(),newEmployee.getGender());
        } else {
            throw new ResourceNotFoundException("Employee not found with id = " + id);
        }
    }

    public void delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            employeeRepository.delete(employee.get());
        }else {
            throw new ResourceNotFoundException("Employee not found by id : " + id);
        }
    }
}
