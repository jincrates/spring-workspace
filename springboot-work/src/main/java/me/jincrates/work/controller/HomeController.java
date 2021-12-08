package me.jincrates.work.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(@AuthenticationPrincipal String memberId) {
        log.info("home controller");
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        log.info("home controller");
        return "/login/login";
    }
}
