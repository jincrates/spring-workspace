package me.jincrates.hr.service.employees;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        if (employee == null || employee.getEmail() == null) {
            throw new RuntimeException("Invalid arguments.");
        }

        final String email = employee.getEmail();

        if (employeeRepository.existsByEmail(email)) {
            log.warning("이미 가입된 이메일입니다. email = " + email);
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        return employeeRepository.save(employee);
    }

    public Employee getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final Employee originalEmployee = employeeRepository.findByEmail(email);

        //matches 메서드를 이용해 패스워드가 같은지 확인
        if (originalEmployee != null && encoder.matches(password, originalEmployee.getPassword())) {
            return originalEmployee;
        }

        return null;
    }

    public List<Employee> retrieveEmployeeAll() {
        return employeeRepository.findAll();
    }

}
