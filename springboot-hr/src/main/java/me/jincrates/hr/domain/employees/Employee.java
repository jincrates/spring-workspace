package me.jincrates.hr.domain.employees;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.BaseEntity;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    //사원 email(id)
    @Column(unique = true)
    private String email;

    //비밀번호
    private String password;

    //사원이름
    private String username;

    //입사일
    private String joinDate;

    //권한
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    //상태(ACTIVE, POSITIVE)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Builder
    public Employee(String email, String password, String username, String joinDate, EmployeeRole role, EmployeeStatus status) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.joinDate = joinDate;
        this.role = role;
        this.status = status;
    }

    public static Employee createEmployee(EmployeeDTO dto, PasswordEncoder passwordEncoder) {
        Employee employee = Employee.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))  //암호화 처리
                .username(dto.getUsername())
                .joinDate(dto.getJoinDate())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
        return employee;
    }
}
