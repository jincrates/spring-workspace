package me.jincrates.work.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal String memberId) {
        log.info("home controller");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        log.info("home controller");
        return "/login/login";
    }

    //로그인을 하지 않는 사용자도 접근할 수 있음
    @GetMapping("/sample/all")
    public void exAll() {
        log.info("exAll...........");
    }

    //로그인한 사용자만 겁근할 수 있음
    @GetMapping("/sample/member")
    public void exMember() {
        log.info("exMember...........");
    }

    //관리자 권한이 있는 사용자만 접근할 수 있음
    @GetMapping("/sample/admin")
    public void exAdmin() {
        log.info("exAdmin...........");
    }
}
