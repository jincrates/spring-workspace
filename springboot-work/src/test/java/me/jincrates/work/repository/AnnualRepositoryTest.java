package me.jincrates.work.repository;

import me.jincrates.work.entity.Annual;
import me.jincrates.work.entity.AnnualUsed;
import me.jincrates.work.entity.Member;
import me.jincrates.work.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
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
    private AnnualUsedRepository annualUsedRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertAnnual() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Optional<Member> findMember = memberRepository.findByEmail("user" + i + "@jincrates.me");
            Member member = new Member();

            if (findMember.isPresent()) {
                member = findMember.get();
                double annual = calculateAnnual(member.getJoinDate());

                Annual annualMember = Annual.builder()
                        .baseYear(2021L)
                        .member(member)
                        .joinDate(member.getJoinDate())
                        .annual(annual)
                        .month(0)
                        .adjusted(0)
                        .used(0)
                        .build();

                annualRepository.save(annualMember);
            }
        });
    }

    @Test
    public void usedAnnual() {
        AnnualUsed annualUsed = AnnualUsed.builder()
                .baseYear(2021L)
                .member("user1@jincrates.me")
                .used(2)
                .usedFromDate(stringToDate("2021-11-24"))
                .usedToDate(stringToDate("2021-11-26"))
                .reason("휴가")
                .build();

        annualUsedRepository.save(annualUsed);
    }

    @Test
    public void findUsedCount() {
        double result = 0;
        String email = "user1@jincrates.me";
        String fromDate = "2021-01-01";
        String toDate = "2021-12-31";

        //result = annualUsedRepository.findUsedCount(email, fromDate, toDate);

        //System.out.println("사용한 연차: " + result);
    }


    public double calculateAnnual(String joinDate) {
        double result = 0;

        //기준일자
        int year = 2021, month = 11, date = 21;
        String baseDate = formatDate(new Date(year - 1900, month - 1, date));

        //근속연수
        double yearsBetween = between("YEARS", joinDate, baseDate);
        double monthsBetween = between("MONTHS", joinDate, baseDate);
        double daysBetween = between("DAYS", joinDate, baseDate);

        String yearFirstDate = joinDate.substring(0, 4) + "-01-01";  //입사연도 시작일
        String yearLastDate = joinDate.substring(0, 4) + "-12-31";  //입사연도 종료일

        // 입사연도의 한해 날짜수(윤년이 껴있는지 확인)
        double leapYearBetween = between("DAYS", yearFirstDate, yearLastDate) + 1;
        double workDaysBetween = between("DAYS", joinDate, yearLastDate);
        double workMonthsBetween = between("MONTHS", joinDate, yearLastDate);

        if (true) {
            // 회계년도 기준
            if (monthsBetween < 24) {
                if (monthsBetween < 12) {
                    // 1년 미만 입사자 : 발생월차 계산(개정연차)
                    result = monthsBetween;
                } else {
                    //비례연차(2년차 연차): 입사년 재직일수/365(윤년은 366))×15일 + 2년차 월차
                    result = Math.round(workDaysBetween / leapYearBetween * 15) + (11 - workMonthsBetween);
                }
            } else {
                // 2년차 이상 연차계산
                result = 15 + (Math.round(monthsBetween / 12.0) + 1) / 2 - 1;
            }

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

        System.out.println("=================================================");
        System.out.println("joinDate : " + joinDate);
        System.out.println("baseDate : " + baseDate);
        System.out.println("yearsBetween : " + yearsBetween);
        System.out.println("monthsBetween : " + monthsBetween);
        System.out.println("daysBetween : " + daysBetween);
        System.out.println("workdaysBetween : " + workDaysBetween);
        System.out.println("workMonthsBetween : " + workMonthsBetween);
        System.out.println("leapYearBetween : " + leapYearBetween);
        System.out.println("=================================================");
        System.out.println("result : " + result);
        System.out.println("=================================================");

        //음수 체크
        if (result < 0) {
            result = 0;
        }

        return result;
    }

    public String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public Date stringToDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double between(String type, String fromDate, String toDate) {
        double result = 0;

        switch (type) {
        case "YEARS":
            result = ChronoUnit.YEARS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate)); break;
        case "MONTHS":
            result = ChronoUnit.MONTHS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate)); break;
        default:
            result = ChronoUnit.DAYS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate)); break;
        }

        return result;
    }
}