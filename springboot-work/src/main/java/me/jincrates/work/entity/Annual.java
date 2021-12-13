package me.jincrates.work.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Annual extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long baseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String joinDate;

    @Column(nullable = false)
    private double annual;  //발생연차

    @Column(nullable = false)
    private double month; //발생월차

    @Column(nullable = false)
    private double adjusted; //조정연차

    @Column(nullable = false)
    private double used; //사용연차

    private double calculate(String joinDate, String baseDate) {
        double result = 0;

        //기준일자
        int year = 2021, month = 1, date = 1;
        baseDate = formatDate(new Date(year - 1900, month - 1, date));

        //근속월일
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

        //음수 체크
        if (result < 0) {
            result = 0;
        }

        return result;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private double between(String type, String fromDate, String toDate) {
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
