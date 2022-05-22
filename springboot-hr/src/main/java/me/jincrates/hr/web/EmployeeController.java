package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.config.security.TokenProvider;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @GetMapping(value = "/login")
    public String loginEmployee() {
        return "employee/employeeLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "employee/employeeLoginForm";
    }

    @PostMapping(value = "/auth/login")
    public String authenticate(EmployeeDTO employeeDTO, Model model) {
        Employee employee = employeeService.getByCredentials(
                employeeDTO.getEmail(),
                employeeDTO.getPassword(),
                passwordEncoder
        );

        if (employee != null) {
            //토큰 생성
            final String token = tokenProvider.create(employee);
            final EmployeeDTO responseEmployee = EmployeeDTO.builder()
                    .email(employee.getEmail())
                    .username(employee.getUsername())
                    .joinDate(employee.getJoinDate())
                    .token(token)
                    .build();

            model.addAttribute("responseEmployee", responseEmployee);
        } else {
            log.warning("loginErrorMsg");
            model.addAttribute("loginErrorMsg", "아이디와 비밀번호를 확인해주세요.");
        }

        return "redirect:/";
    }

    @GetMapping(value = "/employee")
    public String employeeList(Model model) {
        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("employeeList", employeeList);

        return "employee/employeeList";
    }

    @GetMapping(value = "/employee/save")
    public String employeeForm(Model model) {
        model.addAttribute("employeeFormDTO", new EmployeeDTO());
        return "employee/employeeForm";
    }

    @PostMapping(value = "/employee/save")
    public String employeeForm(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) {
        log.info(employeeDTO.toString());
        
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            log.info(sb.toString());

            return "employee/employeeForm";
        }

        try {
            Employee employee = Employee.createEmployee(employeeDTO, passwordEncoder);
            employeeService.create(employee);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "employee/employeeForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/employee/update")
    public String employeeForm(@AuthenticationPrincipal String userId, Model model) {
        Employee employee = employeeRepository.findByEmail(userId);

        EmployeeDTO employeeDTO = new EmployeeDTO(employee);

        model.addAttribute("employeeFormDTO", employeeDTO);
        return "employee/employeeForm";
    }
}
