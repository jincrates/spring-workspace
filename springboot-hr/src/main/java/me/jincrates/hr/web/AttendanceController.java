package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.domain.attendance.AttendanceRepository;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import me.jincrates.hr.service.attendance.AttendanceService;
import me.jincrates.hr.service.employees.EmployeeService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.attendance.AttendanceDTO;
import me.jincrates.hr.web.dto.employees.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@RequiredArgsConstructor
@RequestMapping("/attendance")
@RestController
public class AttendanceController {

    private final EmployeeRepository employeeRepository;
    private final AttendanceService attendanceService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> retrieveAttendanceList(@AuthenticationPrincipal String userId, @Valid @RequestBody AttendanceDTO dto, BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.createAttendance(employee, dto);
            List<Attendance> entityList = attendanceService.retrieve(entity);
            List<AttendanceDTO> dtoList = entityList.stream().map(AttendanceDTO::new).collect(Collectors.toList());

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().data(dtoList).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            //7. 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createAttendance(@AuthenticationPrincipal String userId, @Valid @RequestBody AttendanceDTO dto, BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.createAttendance(employee, dto);
            List<Attendance> entityList = attendanceService.create(entity);
            List<AttendanceDTO> dtoList = entityList.stream().map(AttendanceDTO::new).collect(Collectors.toList());

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().data(dtoList).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            //7. 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
            String error = e.getMessage();

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAttendance(@AuthenticationPrincipal String userId, @Valid @RequestBody AttendanceDTO dto, BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.createAttendance(employee, dto);
            List<Attendance> entityList = attendanceService.update(entity);
            List<AttendanceDTO> dtoList = entityList.stream().map(AttendanceDTO::new).collect(Collectors.toList());

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().data(dtoList).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> removeAttendance(@AuthenticationPrincipal String userId, @Valid @RequestBody AttendanceDTO dto, BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.createAttendance(employee, dto);
            List<Attendance> entityList = attendanceService.delete(entity);
            List<AttendanceDTO> dtoList = entityList.stream().map(AttendanceDTO::new).collect(Collectors.toList());

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().data(dtoList).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?> validateParam(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            sb.append(fieldError.getDefaultMessage());
        }
        ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(sb.toString()).build();
        return ResponseEntity.badRequest().body(response);
    }
}
