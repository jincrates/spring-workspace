package me.jincrates.work.repository;

import me.jincrates.work.entity.Member;
import me.jincrates.work.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;


@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            //1부터 10까지 임의의 번호
            long random = (long) (Math.random() * 10) + 1;

            Member member = Member.builder()
                    .email("user" + i + "@jincrates.me")
                    .password(passwordEncoder.encode("1111"))
                    .username("USER" + i)
                    .joinDate(between("2001-01-01", "2021-12-31"))
                    .status("Y")
                    .build();

            repository.save(member);
        });
    }

    @Test
    public void insertMember() {
        //1부터 10까지 임의의 번호
        long i = 999L;

        Member member = Member.builder()
                .email("user" + i + "@jincrates.me")
                .password(passwordEncoder.encode("1111"))
                .username("USER" + i)
                .joinDate(between("2001-01-01", "2021-12-31"))
                .status("Y")
                .build();

        repository.save(member);
    }

    @Test
    public void encodePassword() {
        String pwd = passwordEncoder.encode("1111");
        System.out.println(pwd);
    }

    public static String between(String fromDate, String toDate) {
        //DateTimeFormatter.ISO_DATE는 "yyyy-mm-dd"를 상수로 선언한 것
        LocalDate startInclusive = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE);
        LocalDate endExclusive = LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE);

        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay).toString();
    }
}