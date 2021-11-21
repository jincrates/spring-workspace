package me.jincrates.work.repository;

import me.jincrates.work.entity.Member;
import me.jincrates.work.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

            calculateAnnual(member.getJoinDate());
        });
    }

    public double calculateAnnual(String joinDate) {
        double result = 0;

        //기준일자
        int year = 2021, month = 3, date = 9;
        String baseDate = formatDate(new Date(year - 1900, month - 1, date));

        //근속연수
        long yearsBetween = ChronoUnit.YEARS.between(LocalDate.parse(joinDate), LocalDate.parse(baseDate));
        long monthsBetween = ChronoUnit.MONTHS.between(LocalDate.parse(joinDate), LocalDate.parse(baseDate));
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.parse(joinDate), LocalDate.parse(baseDate));

        if (monthsBetween <= 24) {
            // 회계년도 기준
            if (monthsBetween < 24) {
                if (monthsBetween < 12) {
                    // 1년 미만 입사자 : 발생월차 계산(개정연차)
                    result = monthsBetween;
                } else {
                    //비례연차: 근무일수/365 * 15
                    //입사하고 회계연도가 바뀐 경우
                    result = (daysBetween / 365.0) * 15.0;
                }

            } else {
                // 2년차 이상 연차계산
                result = 15 + (Math.round(monthsBetween / 12.0) + 1) / 2 - 1;
            }

            System.out.println("=================================================");
            System.out.println("joinDate : " + joinDate);
            System.out.println("baseDate : " + baseDate);
            System.out.println("yearsBetween : " + yearsBetween);
            System.out.println("monthsBetween : " + monthsBetween);
            System.out.println("daysBetween : " + daysBetween);
            System.out.println("=================================================");
            System.out.println("result : " + result);
            System.out.println("=================================================");

        } else {
            // 입사일 기준
            if (monthsBetween < 12) {
                // 1년 미만 입사자 : 발생월차 계산(개정연차)
                result = monthsBetween;
            } else {
                // 2년차 이상 연차계산
                result = 15 + (Math.round(daysBetween / 365.0) + 1) / 2 - 1;
            }
        }

//        System.out.println("=================================================");
//        System.out.println("joinDate : " + joinDate);
//        System.out.println("baseDate : " + baseDate);
//        System.out.println("yearsBetween : " + yearsBetween);
//        System.out.println("monthsBetween : " + monthsBetween);
//        System.out.println("daysBetween : " + daysBetween);
//        System.out.println("=================================================");
//        System.out.println("result : " + result);
//        System.out.println("=================================================");

        //음수 체크
        if (result < 0) {
            result = 0;
        }

        return result;
    }

    public String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}