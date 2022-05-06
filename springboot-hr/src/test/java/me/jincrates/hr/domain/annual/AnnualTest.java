package me.jincrates.hr.domain.annual;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnnualTest {

    @Test
    public void 연차생성() {
        String annualType = "J";        //F:회계연도, J:입사연도
        String joinDate = "2022-12-01"; //입사일자
        String baseDate = "2023-01-01"; //기준일자

        double result = calculate(annualType, joinDate, baseDate);
        System.out.println("발생연차는 " + result + "개 입니다.");
    }

    public double calculate(String annualType, String joinDate, String baseDate) {
        double result = 0;

        //근속연월일
        double yearsBetween = between("YEARS", joinDate, baseDate);
        double monthsBetween = between("MONTHS", joinDate, baseDate);
        double daysBetween = between("DAYS", joinDate, baseDate);

        //1년 미만 입사자: 발생월차(개정연차) 계산
        if (daysBetween < 365.0) {
            result = monthsBetween;

            //회계연도 기준일때 비례연차 계산 추가(비례연차 대상자: 기준일 이전 연도 입사자)
            //비례연차 계산: 전연도 재직일수 / 365 * 15 + 발생월차
            if (annualType.equals("F") && yearsBetween > 0) {
                result += Math.floor(daysBetween / 365.0 * 15);
            }
            //1년 이상 연차계산: 최초발생 연차 + ((근속연수 - 1년) / 2) 계산 후 나머지는 버림
        } else {
            result = 15 + Math.floor((monthsBetween / 12.0 - 1) / 2.0);
        }

        //음수 체크
        if (result < 0) {
            result = 0;
        }

        System.out.println("yearsBetween = " + yearsBetween);
        System.out.println("monthsBetween = " + monthsBetween);
        System.out.println("daysBetween = " + daysBetween);

        return result;
    }

    private double between(String type, String fromDate, String toDate) {
        if (type.equals("YEARS")) {
            return Math.abs(LocalDate.parse(fromDate).getYear() - LocalDate.parse(toDate).getYear());
        } else if (type.equals("MONTHS")) {
            return ChronoUnit.MONTHS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
        } else {
            return ChronoUnit.DAYS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
        }
    }
}