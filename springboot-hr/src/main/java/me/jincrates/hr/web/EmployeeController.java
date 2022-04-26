package me.jincrates.hr.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import me.jincrates.hr.config.security.TokenProvider;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @ApiOperation(value="회원가입", notes="사원 계정을 생성.")
    @PostMapping(value = "/api/auth/signup")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDTO dto, BindingResult bindingResult) {

        //1. 유효성 검사
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                sb.append(fieldError.getDefaultMessage());
            }
            ResponseDTO<EmployeeDTO> response = ResponseDTO.<EmployeeDTO>builder().error(sb.toString()).build();
            return ResponseEntity.badRequest().body(response);
        }

        try {
            //2. DTO를 Entity로 변환한다.
            Employee entity = Employee.createEmployee(dto, passwordEncoder);

            //3. 서비스를 이용해 Employee 엔티티를 생성한다.
            Employee registerEmployee = employeeService.create(entity);

            //4. 사용자 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 EmployeeDTO 리턴
            EmployeeDTO response = EmployeeDTO.builder()
                    .email(registerEmployee.getEmail())
                    .username(registerEmployee.getUsername())
                    .joinDate(registerEmployee.getJoinDate())
                    .role(registerEmployee.getRole())
                    .status(registerEmployee.getStatus())
                    .build();

            //5. EmployeeDTO 리턴한다.
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            //6. 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<EmployeeDTO> response = ResponseDTO.<EmployeeDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/api/auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.getByCredentials(
                employeeDTO.getEmail(),
                employeeDTO.getPassword(),
                passwordEncoder
        );

        if (employee != null) {
            //토큰 생성
            final String token = tokenProvider.create(employee);
            final EmployeeDTO responseEmployee = EmployeeDTO.builder()
                    .email(employee.getEmail())
                    .username(employee.getUsername())
                    .joinDate(employee.getJoinDate())
                    .role(employee.getRole())
                    .status(employee.getStatus())
                    .token(token)
                    .build();

            return ResponseEntity.ok().body(responseEmployee);
        } else {
            ResponseDTO response = ResponseDTO.builder().error("Login failed.").build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
