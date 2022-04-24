package me.jincrates.hr.domain.employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String empEmail);

    Boolean existsByEmail(String email);
}
