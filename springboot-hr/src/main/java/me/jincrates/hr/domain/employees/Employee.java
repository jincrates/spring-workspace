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
@Table(name="Employee")
public class Employee {//extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String empId;

    private String empPwd;

    private String empNm;

    private String joinDate;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Builder
    public Employee(String empId, String empPwd, String empNm, String joinDate, EmployeeRole role, EmployeeStatus status) {
        this.empId = empId;
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
                .empId(dto.getEmpId())
                .empPwd(dto.getEmpPwd())  //암호화 처리
                .empNm(dto.getEmpNm())
                .joinDate(dto.getJoinDate())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
        return employee;
    }
}
