package me.jincrates.hr.service.attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.domain.attendance.AttendanceRepository;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public List<Attendance> checkIn(Attendance entity) {
        attendanceRepository.save(entity);

        log.info("Entity Attendance employee :" + entity.getEmployee().getEmail());
        log.info("Entity Attendance workDate :" + entity.getWorkDate());

        return attendanceRepository.findByEmployeeId(entity.getEmployee().getId());
    }
}
