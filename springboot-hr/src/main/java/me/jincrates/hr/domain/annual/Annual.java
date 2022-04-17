package me.jincrates.hr.domain.annual;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Annual")
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //사원
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
}
