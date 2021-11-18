package me.jincrates.work.repository;

import me.jincrates.work.entity.Member;
import me.jincrates.work.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnnualRepositoryTest {

    @Autowired
    private AnnualRepository annualRepository;

    @Autowired
    private MemberRepository memberRepository;



    @Test
    public void insertAnnual() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Optional<Member> findMember = memberRepository.findByEmail("user" + i + "@jincrates.me");
            Member member = new Member();

            if (findMember.isPresent()) {
                member = findMember.get();
            }

            System.out.println(member.getJoinDate());
        });
    }
}