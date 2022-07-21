package me.jincrates.studymanager.main;

import lombok.RequiredArgsConstructor;
import me.jincrates.studymanager.account.CurrentAccount;
import me.jincrates.studymanager.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {

        if (account != null) {
            model.addAttribute(account);
        }

        return "index";
    }
}
