package me.jincrates.hr.domain.annual;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@NoArgsConstructor
@Entity
@Table(name="annual")
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "annual_id")
    private Long id;

    //사원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //사원 입사일
    private String empJoinDate;

    //근속연수
    private double lengthOfService;

    //발생월차
    private double hadMonthly;

    //발생연차
    private double hadAnnual;

    //조정연차
    private double adjustedAnnual;

    //사용연차
    private double usedAnnual;

    private double calculateMonthly(String annualType, String joinDate, String baseDate) {
        double result = 0;

        //근속연월일
        double monthsBetween = betweenMonths(joinDate, baseDate);
        double daysBetween = betweenDays(joinDate, baseDate);

        //1년 미만 입사자: 발생월차(개정연차) 계산
        if (daysBetween < 365.0) {
            result = monthsBetween;
        }

        //음수 체크
        return (result < 0) ? 0 : result;
    }

    public double calculateAnnual(String annualType, String joinDate, String baseDate) {
        double result = 0;

        //근속연월일
        double yearsBetween = betweenYears(joinDate, baseDate);
        double monthsBetween = betweenMonths(joinDate, baseDate);
        double daysBetween = betweenDays(joinDate, baseDate);

        //1년 미만 연차계산
        if (daysBetween < 365.0) {
            //회계연도 기준일때 비례연차 계산 추가(비례연차 대상자: 기준일 이전 연도 입사자)
            //비례연차 계산: 전연도 재직일수 / 365 * 15
            if (annualType.equals("F") && yearsBetween > 0) {
                result += Math.floor(daysBetween / 365.0 * 15);
            }
            //1년 이상 연차계산: 최초발생 연차 + ((근속연수 - 1년) / 2) 계산 후 나머지는 버림
        } else {
            result = 15 + Math.floor((monthsBetween / 12.0 - 1) / 2.0);
        }

        //음수 체크
        return (result < 0) ? 0 : result;
    }

    private double betweenYears(String fromDate, String toDate) {
        return Math.abs(LocalDate.parse(fromDate).getYear() - LocalDate.parse(toDate).getYear());
    }

    private double betweenMonths(String fromDate, String toDate) {
        return ChronoUnit.MONTHS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
    }

    private double betweenDays(String fromDate, String toDate) {
        return ChronoUnit.DAYS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
    }
}
