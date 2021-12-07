package me.jincrates.work.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.entity.Member;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member create(Member member) {
        if(member == null || member.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }

        String email = member.getEmail();

        if(memberRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException(String.format("Email[%s] not found", email));
        }
        return  member;
    }

    public Member getByCredentials(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }
}

