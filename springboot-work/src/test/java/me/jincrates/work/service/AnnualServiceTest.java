package me.jincrates.work.service;

import me.jincrates.work.dto.AnnualDTO;
import me.jincrates.work.entity.Annual;
import me.jincrates.work.entity.Member;
import me.jincrates.work.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnnualServiceTest {

    @Autowired
    private AnnualService annualService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createAnnual() {
        Member findMember = memberRepository.findByEmail("user1@jincrates.me");

        if (findMember == null) {
            return;
        }

        AnnualDTO annualDTO = AnnualDTO.builder()
                .baseYear(2021L)
                .member(findMember)
                .joinDate(findMember.getJoinDate())
                .annual(calculateAnnual(findMember.getJoinDate()))
                .month(0)
                .adjusted(0)
                .used(0)
                .build();

        annualService.createAnnual(annualDTO);
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