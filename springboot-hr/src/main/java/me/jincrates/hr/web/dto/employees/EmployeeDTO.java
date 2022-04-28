package me.jincrates.hr.web.dto.employees;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(description = "사원 데이터 전송 객체")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @Schema(description = "jwt 토큰", type = "string")
    private String token;

    @Schema(description = "이메일", type = "string", required = true)
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호", type = "string", required = true)
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @Schema(description = "사원명", type = "string", required = true)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @Schema(description = "입사일", type = "LocalDate", required = true)
    @NotNull(message = "입사일은 필수 입력 값입니다.")
    private LocalDate joinDate;

    @Schema(description = "권한", type = "EmployeeRole", required = true)
    private EmployeeRole role;

    @Schema(description = "상태", type = "EmployeeStatus", required = true)
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



