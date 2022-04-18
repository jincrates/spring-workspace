package me.jincrates.hr.domain.employees;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.BaseEntity;
import me.jincrates.hr.web.dto.employees.EmployeeFormDTO;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee {//extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    //사원 email(id)
    @Column(unique = true)
    private String empEmail;

    //비밀번호
    private String empPwd;

    //사원이름
    private String empNm;

    //입사일
    private String joinDate;

    //권한
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    //상태(ACTIVE, POSITIVE)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Builder
    public Employee(String empEmail, String empPwd, String empNm, String joinDate, EmployeeRole role, EmployeeStatus status) {
        this.empEmail = empEmail;
        this.empPwd = empPwd;
        this.empNm = empNm;
        this.joinDate = joinDate;
        this.role = role;
        this.status = status;
    }

    /*
    public static Employee createEmployee(EmployeeFormDTO dto, PasswordEncoder passwordEncoder) {
        Employee employee = Employee.builder()
                .empId(dto.getEmpId())
                .empPwd(passwordEncoder.encode(dto.getEmpPwd()))  //암호화 처리
                .empNm(dto.getEmpNm())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
        return employee;
    }
     */
    public static Employee createEmployee(EmployeeFormDTO dto) {
        Employee employee = Employee.builder()
                .empEmail(dto.getEmpEmail())
                .empPwd(dto.getEmpPwd())  //암호화 처리
                .empNm(dto.getEmpNm())
                .joinDate(dto.getJoinDate())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
        return employee;
    }
}
