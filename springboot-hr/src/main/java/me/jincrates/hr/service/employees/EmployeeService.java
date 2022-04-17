package me.jincrates.hr.service.employees;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> create(Employee employee) {
        //중복 체크유효성
        validateDuplicateEmployee(employee);
        employeeRepository.save(employee);

        log.info("Entity empId : " + employee.getEmpId() + " is saved.");

        return employeeRepository.findByEmpId(employee.getEmpId());
    }

    public void validateDuplicateEmployee(Employee employee) {
        List<Employee> findEmployee = employeeRepository.findByEmpId(employee.getEmpId());
        if (findEmployee.size() > 0) {
            throw new IllegalStateException("이미 가입된 사용자입니다.");
        }
    }

    public void validate(Employee entity) {
        if (entity == null) {
            log.warning("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getEmpId() == null) {
            log.warning("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }

    }

}
