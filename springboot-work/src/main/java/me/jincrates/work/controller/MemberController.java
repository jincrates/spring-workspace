package me.jincrates.work.controller;

import me.jincrates.work.dto.MemberDTO;
import me.jincrates.work.dto.ResponseDTO;
import me.jincrates.work.entity.Member;
import me.jincrates.work.security.TokenProvider;
import me.jincrates.work.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("")
    public String list(Model model) {
        List<Member> members = service.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/{email}")
    public Member findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/new")
    public String createMember(Model model) {
        model.addAttribute("createMember", new MemberDTO());
        return "members/memberCreateForm";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerMember(@RequestBody MemberDTO memberDTO) {
        try {
            //요청을 이용해 저장할 사용자 만들기
            Member member = Member.builder()
                    .email(memberDTO.getEmail())
                    .username(memberDTO.getUsername())
                    .password(memberDTO.getPassword())
                    .joinDate(memberDTO.getJoinDate())
                    .status("Y")
                    .build();

            //서비스를 이용해 리포지터리에 사용자 저장
            Member registeredMember = service.create(member);

            MemberDTO registerMemberDTO = MemberDTO.builder()
                    .email(registeredMember.getEmail())
                    .id(registeredMember.getId())
                    .username(registeredMember.getUsername())
                    .build();

            return ResponseEntity.ok().body(registerMemberDTO);
        } catch (Exception e) {

            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody MemberDTO memberDTO) {
        Member member = service.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword());

        if (member != null) {
            String token = tokenProvider.create(member);

            MemberDTO responseMemberDTO = MemberDTO.builder()
                    .id(member.getId())
                    .token(token)
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .username(member.getUsername())
                    .joinDate(member.getJoinDate())
                    .build();

            return ResponseEntity.ok().body(responseMemberDTO);

        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed")
                    .build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
