package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    //private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/new")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO dto, BindingResult bindingResult) {

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
            //Employee entity = Employee.createEmployee(dto, passwordEncoder);
            Employee entity = Employee.createEmployee(dto);

            //3. 서비스를 이용해 Employee 엔티티를 생성한다.
            List<Employee> entityList = employeeService.create(entity);

            //4. 자바 스트림을 이용해 리턴된 entityList를 dtoList로 변환한다.
            List<EmployeeDTO> dtoList = entityList.stream().map(EmployeeDTO::new).collect(Collectors.toList());

            //5. 변환된 dtoList를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<EmployeeDTO> response = ResponseDTO.<EmployeeDTO>builder().data(dtoList).build();

            //6. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            //7. 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<EmployeeDTO> response = ResponseDTO.<EmployeeDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
