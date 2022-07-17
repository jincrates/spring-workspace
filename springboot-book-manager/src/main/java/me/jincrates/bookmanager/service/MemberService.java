package me.jincrates.bookmanager.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.bookmanager.domain.members.Member;
import me.jincrates.bookmanager.domain.members.MemberRepository;
import me.jincrates.bookmanager.web.dto.MemberDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
