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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String token;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "입사일은 필수 입력 값입니다.")
    private String joinDate;

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

    public static Employee toEntity(Employee dto) {
        return Employee.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .joinDate(dto.getJoinDate())
                .role(dto.getRole())
                .build();
    }
}
