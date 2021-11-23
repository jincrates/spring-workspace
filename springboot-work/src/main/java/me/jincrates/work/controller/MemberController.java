package me.jincrates.work.controller;

import me.jincrates.work.entity.Member;
import me.jincrates.work.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("")
    public List<Member> findAll(Model model) {
        return service.findAll();
    }

    @GetMapping("/{email}")
    public Member findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }
}