package com.furkanbalikci.simplecrudapp.dto;

import com.furkanbalikci.simplecrudapp.model.Company;
import com.furkanbalikci.simplecrudapp.model.Employee;
import com.furkanbalikci.simplecrudapp.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private int age;
    private Gender gender;
    private String department;
    private String companyName;

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setGender(employee.getGender());
        dto.setDepartment(employee.getDepartment());
        if (employee.getCompany() != null) {
            dto.setCompanyName(employee.getCompany().getName());
        }
        return dto;
    }

    public static Employee toEntity(EmployeeDTO employeeDTO, Company company) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setGender(employeeDTO.getGender());
        employee.setDepartment(employeeDTO.getDepartment());
        if (employeeDTO.getCompanyName() != null) {
            company.setName(employeeDTO.getCompanyName());
            employee.setCompany(company);
        }
        return employee;
    }

    public static List<EmployeeDTO> toListDTO(List<Employee> employees) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee employee : employees) {
            dtos.add(toDTO(employee));
        }
        return dtos;
    }


}
