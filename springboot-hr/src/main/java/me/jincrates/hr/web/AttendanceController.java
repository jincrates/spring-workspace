package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.service.attendance.AttendanceService;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.attendance.AttendanceDTO;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/attendance")
@RestController
public class AttendanceController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;

    @PostMapping(value = "checkIn")
    public ResponseEntity<?> createAttendance(@Valid  @RequestBody AttendanceDTO dto, BindingResult bindingResult) {

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

            ResponseDTO<AttendanceDTO> response = null;

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
