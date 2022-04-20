package me.jincrates.hr.web.dto.annual;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.annual.Annual;
import me.jincrates.hr.domain.employees.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnualDTO {

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

    public AnnualDTO(Annual entity) {
        this.employee = entity.getEmployee();
        this.empJoinDate = entity.getEmpJoinDate();
        this.lengthOfService = entity.getLengthOfService();
        this.hadMonthly = entity.getHadMonthly();
        this.hadAnnual = entity.getHadAnnual();
        this.adjustedAnnual = entity.getAdjustedAnnual();
        this.usedAnnual = entity.getUsedAnnual();
    }
}
