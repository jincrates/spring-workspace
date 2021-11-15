package com.jincrates.cogito.security;

import com.jincrates.cogito.entity.Member;
import com.jincrates.cogito.entity.MemberRole;
import com.jincrates.cogito.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        //1 - 80까지는 USER만 지정
        //81 - 90까지는 USER, MANAGER
        //91 - 100까지는 USER, MANAGER, ADMIN
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("member" + i + "@cogito.com")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();
            //default role
            member.addUserRole(MemberRole.USER);

            if( i > 80) {
                member.addUserRole(MemberRole.MANAGER);
            }

            if (i > 90) {
                member.addUserRole(MemberRole.ADMIN);
            }

            repository.save(member);
        });
    }

    @Test
    public void testRead() {

        Optional<Member> result = repository.findByEmail("member94@cogito.com", false);

        Member member = result.get();

        System.out.println(member);
    }
}
