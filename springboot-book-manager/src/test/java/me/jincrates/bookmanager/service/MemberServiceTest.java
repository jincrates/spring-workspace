package me.jincrates.bookmanager.service;

import me.jincrates.bookmanager.common.Status;
import me.jincrates.bookmanager.domain.members.Member;
import me.jincrates.bookmanager.domain.members.MemberRole;
import me.jincrates.bookmanager.web.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    public Member createMember() {
        MemberDto memberDto =  MemberDto.builder()
                .name("사용자")
                .email("user1@email.com")
                .password("1111")
                .phoneNumber("010-1111-2222")
                .role(MemberRole.USER)
                .status(Status.ACTIVE)
                .build();
        return Member.createMember(memberDto);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertNotNull(savedMember);
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getPhoneNumber(), savedMember.getPhoneNumber());
        assertEquals(member.getRole(), savedMember.getRole());
        assertEquals(member.getStatus(), savedMember.getStatus());
    }

    @Test
    @DisplayName("중복회원 가입 테스트")
    public void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}