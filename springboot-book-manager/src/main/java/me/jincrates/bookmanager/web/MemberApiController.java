package me.jincrates.bookmanager.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.bookmanager.domain.members.Member;
import me.jincrates.bookmanager.service.MemberService;
import me.jincrates.bookmanager.web.dto.MemberDto;
import me.jincrates.bookmanager.web.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/members")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping(value = "/new")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberDto memberDto) {
        try {
            System.out.println(memberDto);
            Member entity = MemberDto.toEntity(memberDto);
            Member savedMember = memberService.saveMember(entity);

            MemberDto response = Member.of(savedMember);

            return ResponseEntity.ok().body(response.toString());
        } catch (Exception e) {
            ResponseDto<Object> response = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
