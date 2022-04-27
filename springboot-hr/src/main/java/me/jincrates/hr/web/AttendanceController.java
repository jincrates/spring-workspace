package me.jincrates.hr.web;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import me.jincrates.hr.service.attendance.AttendanceService;
import me.jincrates.hr.web.dto.ResponseDTO;
import me.jincrates.hr.web.dto.attendance.AttendanceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "attendance", description = "출퇴근 API")
@RequiredArgsConstructor
@RequestMapping("/attendance")
@RestController
public class AttendanceController {

    private final EmployeeRepository employeeRepository;
    private final AttendanceService attendanceService;

    @Operation(summary="출퇴근 리스트", description="로그인한 사원의 출퇴근 리스트를 리턴합니다.")
    @GetMapping(value = "/list")
    public ResponseEntity<?> retrieveAttendanceList(
            @Parameter(name = "email", description = "로그인한 사원의 email") @AuthenticationPrincipal String userId) {
        try {
            Employee employee = employeeRepository.findByEmail(userId);
            List<Attendance> entityList = attendanceService.retrieve(employee.getId());
            List<AttendanceDTO> dtoList = entityList.stream().map(AttendanceDTO::new).collect(Collectors.toList());

            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().data(dtoList).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<AttendanceDTO> response = ResponseDTO.<AttendanceDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary="출퇴근 등록", description="출퇴근 데이터를 생성합니다.")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createAttendance(
            @Parameter(name = "userId", description = "로그인한 사원의 email") @AuthenticationPrincipal String userId,
            @Parameter(name = "attendanceDTO", description = "출퇴근 전송 객체") @Valid @RequestBody AttendanceDTO dto,
            BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.toEntity(employee, dto);
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

    @Operation(summary="출퇴근 수정", description="출퇴근 데이터를 수정합니다.")
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAttendance(
            @Parameter(name = "userId", description = "로그인한 사원의 email") @AuthenticationPrincipal String userId,
            @Parameter(name = "attendanceDTO", description = "출퇴근 전송 객체") @Valid @RequestBody AttendanceDTO dto,
            BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.toEntity(employee, dto);
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

    @Operation(summary="출퇴근 삭제", description="출퇴근 데이터를 삭제합니다.")
    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> removeAttendance(
            @Parameter(name = "userId", description = "로그인한 사원의 email") @AuthenticationPrincipal String userId,
            @Parameter(name = "attendanceDTO", description = "출퇴근 전송 객체") @Valid @RequestBody AttendanceDTO dto,
            BindingResult bindingResult) {
        //유효성 검사
        if (bindingResult.hasErrors()) {
            return validateParam(bindingResult);
        }

        try {
            Employee employee = employeeRepository.findByEmail(userId);

            Attendance entity = Attendance.toEntity(employee, dto);
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
