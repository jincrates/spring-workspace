package me.jincrates.hr.web.dto.employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRole;
import me.jincrates.hr.domain.employees.EmployeeStatus;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String token;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotNull(message = "입사일은 필수 입력 값입니다.")
    private LocalDate joinDate;

    private EmployeeRole role;

    private EmployeeStatus status;

    public EmployeeDTO(Employee entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.username = entity.getUsername();
        this.joinDate = entity.getJoinDate();
        this.role = entity.getRole();
        this.status = entity.getStatus();
    }
}
