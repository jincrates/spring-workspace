package me.jincrates.work.controller;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.dto.MemberDTO;
import me.jincrates.work.dto.PageRequestDTO;
import me.jincrates.work.dto.ResponseDTO;
import me.jincrates.work.entity.Member;
import me.jincrates.work.security.TokenProvider;
import me.jincrates.work.service.MemberService;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", service.findAll());

        return "members/memberList";
    }

    @GetMapping("/members/{email}")
    public Member findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/members/new")
    public String createMember(Model model) {
        model.addAttribute("createMember", new MemberDTO());

        return "members/memberCreateForm";
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerMember(@RequestBody MemberDTO memberDTO) {
        try {
            //요청을 이용해 저장할 사용자 만들기
            Member member = Member.builder()
                    .email(memberDTO.getEmail())
                    .username(memberDTO.getUsername())
                    .password(passwordEncoder.encode(memberDTO.getPassword()))
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

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticate(HttpSession session, @RequestBody MemberDTO memberDTO) {
        Member member = service.getByCredentials(memberDTO.getEmail(), memberDTO.getPassword(), passwordEncoder);
        log.info("sign ================================================");
        log.info(member.toString());

        if (member != null) {
            // 세션값 설정
            session.setAttribute("user_id", member.getEmail());
            session.setAttribute("user_name", member.getUsername());

            // 세션 유지시간 설정(초단위)
            // 60 * 30 = 30분
            //session.setMaxInactiveInterval(60 * 30);

            // 세션 시간을 무한대로 설정
            session.setMaxInactiveInterval(-1);

            String token = tokenProvider.create(member);

            MemberDTO responseMemberDTO = MemberDTO.builder()
                    .id(member.getId())
                    .token(token)
                    .email(member.getEmail())
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
