package me.jincrates.studymanager.account;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());  // 이름이 같다면 이렇게도 쓸 수 있다.
        //model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up";
    }
}
