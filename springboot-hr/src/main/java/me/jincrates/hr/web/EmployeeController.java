package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class EmployeeController {

    @GetMapping(value = "/login")
    public String loginEmployee() {
        return "employee/employeeLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "employee/employeeLoginForm";
    }

    @GetMapping(value = "/employee/save")
    public String employeeForm() {
        return "employee/employeeForm";
    }
}
