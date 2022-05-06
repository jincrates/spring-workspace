package me.jincrates.hr.domain.annual;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;

import javax.persistence.*;

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



    //회계연도 기준
    //근속연수= 현재연도 - 입사연도
    //1. 회계연도 생성 기준일로부터 1년 이상 근무자인지?
    //1-a. 이상이다. 근속연수에 비례하여 연차 부여: 15 + (근속연수/2)
    //1-b. 미만이다. 근무한 월만큼 연차부여(최대 11개)
    //1-c. 비례연차 대상자의 경우


}
