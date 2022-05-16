package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String loginEmployee() {
        return "employee/employeeLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "employee/employeeLoginForm";
    }

    @GetMapping(value = "/employee")
    public String employeeList() {
        return "employee/employeeList";
    }

    @GetMapping(value = "/employee/save")
    public String employeeForm(Model model) {
        model.addAttribute("employeeFormDTO", new EmployeeDTO());
        return "employee/employeeForm";
    }

    @PostMapping(value = "/employee/save")
    public String employeeForm(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) {
        System.out.println(employeeDTO.toString());
        
        if (bindingResult.hasErrors()) {
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
}
