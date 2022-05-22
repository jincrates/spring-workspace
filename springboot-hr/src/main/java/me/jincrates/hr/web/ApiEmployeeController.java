package me.jincrates.hr.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.config.security.TokenProvider;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "employee", description = "사원 API")
@Log
@RequiredArgsConstructor
@RestController
public class ApiEmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Operation(summary="회원가입", description="사원 계정을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PostMapping(value = "/api/auth/signup")
    public ResponseEntity<?> registerEmployee(
            @Parameter(name = "employeeDTO", description = "사원 전송 객체") @Valid @RequestBody EmployeeDTO dto,
            BindingResult bindingResult) {

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

    @Operation(summary="로그인", description="이메일과 비밀번호를 입력받아 jwt token 발급.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PostMapping(value = "/api/auth/signin")
    public ResponseEntity<?> authenticate(@Parameter(name = "employeeDTO", description = "사원 전송 객체") @RequestBody EmployeeDTO employeeDTO) {
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

    @Operation(summary="전체 사원조회", description="전체 사원정보 조회합니다.")
    @GetMapping(value = "/api/employee/all")
    public ResponseEntity<?> retrieveEmployeeList() {
        //String temporaryUserId = "temporary-user";

        //1. 서비스 메서드의 retrieveEmployeeAll() 메서드를 사용
        List<Employee> entities = employeeService.retrieveAll();

        //2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 Employee 리스트로 변환한다.
        List<EmployeeDTO> dtos = entities.stream().map(EmployeeDTO::new).collect(Collectors.toList());

        //3. 변환된 Employee 리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<EmployeeDTO> response = ResponseDTO.<EmployeeDTO>builder().data(dtos).build();

        //4. ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary="사원정보 조회", description="사원정보 조회합니다.")
    @GetMapping(value = "/api/employee")
    public ResponseEntity<?> retrieveEmployee(@AuthenticationPrincipal String userId) {

        //1. Entity 가져오기
        Employee entity = employeeService.retrieve(userId);

        //2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 Employee 리스트로 변환한다.
        EmployeeDTO response = EmployeeDTO.of(entity);

        //3. ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary="사원정보 수정", description="사원정보를 수정합니다.")
    @PutMapping(value = "/api/employee/update")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDTO dto, BindingResult bindingResult) {

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
            Employee updatedEmployee = employeeService.update(dto);

            EmployeeDTO response = EmployeeDTO.builder()
                    .email(updatedEmployee.getEmail())
                    .username(updatedEmployee.getUsername())
                    .joinDate(updatedEmployee.getJoinDate())
                    .role(updatedEmployee.getRole())
                    .status(updatedEmployee.getStatus())
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

}
