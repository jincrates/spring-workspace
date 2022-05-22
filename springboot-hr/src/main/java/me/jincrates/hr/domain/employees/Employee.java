package me.jincrates.hr.domain.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.BaseEntity;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;

@Schema(description = "사원")
@Getter
@NoArgsConstructor
@Entity
@Table(name="employee")
public class Employee extends BaseEntity {
    @Schema(description = "사원 PK")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Schema(description = "이메일(id)")
    @Column(unique = true)
    private String email;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "사원명")
    private String username;

    @Schema(description = "입사일자", example = "yyyy-MM-dd")
    private LocalDate joinDate;

    @Schema(description = "권한", allowableValues = {"ADMIN", "USER"})
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @Schema(description = "상태", allowableValues = {"ACTIVE", "INACTIVE"})
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Builder
    public Employee(String email, String password, String username, LocalDate joinDate, EmployeeRole role, EmployeeStatus status) {
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

    public void update(String username, String password, EmployeeRole role, EmployeeStatus status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
