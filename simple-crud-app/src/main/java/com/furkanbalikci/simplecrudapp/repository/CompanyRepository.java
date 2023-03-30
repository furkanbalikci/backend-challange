package com.furkanbalikci.simplecrudapp.repository;

import com.furkanbalikci.simplecrudapp.model.Company;
import com.furkanbalikci.simplecrudapp.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Modifying
    @Query("update company c set c.name = :name, c.numberOfEmployees = :numberOfEmployees, c.industries = :industries where c.id = :id")
    void updateCompanyById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("numberOfEmployees") Integer numberOfEmployees,
            @Param("industries") String industries
    );
    Optional<Company> findByName(String name);

}