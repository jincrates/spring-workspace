package me.jincrates.hr.domain.annual;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.web.dto.annual.AnnualDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Schema(description = "연차")
@Getter
@NoArgsConstructor
@Entity
@Table(name="annual")
public class Annual {
    @Schema(description = "연차 PK")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "annual_id")
    private Long id;

    //사원
    @Schema(description = "사원 PK")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //근속연수
    @Schema(description = "근속연수")
    private double lengthOfService;

    //발생월차
    @Schema(description = "발생월차")
    private double hadMonthly;

    //발생연차
    @Schema(description = "발생연차")
    private double hadAnnual;

    //조정연차
    @Schema(description = "조정연차")
    private double adjustedAnnual;

    //사용연차
    @Schema(description = "사용연차")
    private double usedAnnual;

    @Builder
    public Annual(Employee employee, double lengthOfService, double hadMonthly, double hadAnnual, double adjustedAnnual, double usedAnnual) {
        this.employee = employee;
        this.lengthOfService = lengthOfService;
        this.hadMonthly = hadMonthly;
        this.hadAnnual = hadAnnual;
        this.adjustedAnnual = adjustedAnnual;
        this.usedAnnual = usedAnnual;
    }

    public static Annual toEntity(Employee employee, AnnualDTO dto, String annualType, LocalDate basedDate) {
        return Annual.builder()
                .employee(employee)
                .lengthOfService(betweenYears(employee.getJoinDate().toString(), basedDate.toString()))
                .hadMonthly(calculateMonthly(annualType, employee.getJoinDate().toString(), basedDate.toString()))
                .hadAnnual(calculateAnnual(annualType, employee.getJoinDate().toString(), basedDate.toString()))
                .adjustedAnnual(dto.getAdjustedAnnual())
                .usedAnnual(dto.getUsedAnnual())
                .build();
    }

    private static double calculateMonthly(String annualType, String joinDate, String baseDate) {
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

    public static double calculateAnnual(String annualType, String joinDate, String baseDate) {
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

    private static double betweenYears(String fromDate, String toDate) {
        return Math.abs(LocalDate.parse(fromDate).getYear() - LocalDate.parse(toDate).getYear());
    }

    private static double betweenMonths(String fromDate, String toDate) {
        return ChronoUnit.MONTHS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
    }

    private static double betweenDays(String fromDate, String toDate) {
        return ChronoUnit.DAYS.between(LocalDate.parse(fromDate), LocalDate.parse(toDate));
    }
}
