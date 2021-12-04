package me.jincrates.work.repository;

import me.jincrates.work.entity.Annual;
import me.jincrates.work.entity.AnnualUsed;
import me.jincrates.work.entity.Member;
import me.jincrates.work.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
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
                double annual = calculate(member.getJoinDate());

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
    public void saveUsed() {
        AnnualUsed annualUsed = AnnualUsed.builder()
                .baseYear(2021L)
                .member("user1@jincrates.me")
                .used(3)
                .usedFromDate(convertToDate("2021-12-01"))
                .usedToDate(convertToDate("2021-12-03"))
                .reason("테스트")
                .build();

        annualUsedRepository.save(annualUsed);
    }

    @Test
    public void removeUsed() {
        Long usedId = 1L;

        annualUsedRepository.deleteById(usedId);
    }

    @Test
    @Transactional
    public void modifyUsed() {
        Long usedId = 9L;
        AnnualUsed finded = annualUsedRepository.getById(usedId);




        System.out.println(finded.toString());
    }

    @Test
    public void findUsedCount() {
        double result = 0;
        String email = "user1@jincrates.me";

        Optional<Double> findUsed = annualUsedRepository.findUsedCount(email);

        if (findUsed.isPresent()) {
            result = findUsed.get();
        }

        System.out.println("사용한 연차: " + result);
    }

    @Test
    public void findUsedList() {
        List<AnnualUsed> annuaUsedlList = annualUsedRepository.findAll();

        System.out.println("사용한 연차 리스트===============================================");
        System.out.println(annuaUsedlList);
    }



    public double calculate(String joinDate) {
        double result = 0;

        //기준일자
        int year = 2021, month = 1, date = 1;
        String baseDate = formatDate(new Date(year - 1900, month - 1, date));

        //근속연수
        double yearsBetween = between("YEARS", joinDate, baseDate);
        double monthsBetween = between("MONTHS", joinDate, baseDate);
        double daysBetween = between("DAYS", joinDate, baseDate);

        //비례연차 대상자: 기준일자의 연도보다 입사년도가 작은 경우(기준일 전녀도 입사자)
        boolean isProportional = Integer.parseInt(joinDate.substring(0, 4)) < Integer.parseInt(baseDate.substring(0, 4));

        if (true) {
            // 회계년도 기준
            if (daysBetween < 365) {
                // 1년 미만 입사자 : 발생월차 계산(개정연차)
                result = monthsBetween;

                //비례연차 계산: 전년도 재직일수 / 365 * 15 + 발생월차
                if (isProportional) {
                    result = Math.floor(daysBetween / 365.0 * 15) + monthsBetween;
                }
            } else {
                // 1년차 이상 연차계산: 최초발생연차 + (근속년수 - 1년) / 2 계산후 나머지는 버림
                result = 15 + Math.floor((monthsBetween / 12.0 - 1) / 2.0);
            }
        } else {
            // 입사일 기준
            if (daysBetween < 365) {
                // 1년 미만 입사자 : 발생월차 계산(개정연차)
                result = monthsBetween;
            } else {
                // 1년차 이상 연차계산: 최초발생연차 + (근속년수 - 1년) / 2 계산후 나머지는 버림
                result = 15 + Math.floor((monthsBetween / 12.0 - 1) / 2.0);
            }
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

        //음수 체크
        if (result < 0) {
            result = 0;
        }

        return result;
    }

    public String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public Date convertToDate(String dateStr) {
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