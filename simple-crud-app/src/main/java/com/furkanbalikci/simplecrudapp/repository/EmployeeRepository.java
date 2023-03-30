package com.furkanbalikci.simplecrudapp.repository;

import com.furkanbalikci.simplecrudapp.model.Employee;
import com.furkanbalikci.simplecrudapp.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<List<Employee>> findByCompanyId(Long id);
    @Modifying
    @Query("update employee e set e.name = :name, e.companyId = :companyId, e.department = :department, e.age = :age, e.gender = :gender where e.id = :id")
    void updateEmployeeById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("companyId") Long companyId,
            @Param("department") String department,
            @Param("age") Integer age,
            @Param("gender") Gender gender
    );


}
