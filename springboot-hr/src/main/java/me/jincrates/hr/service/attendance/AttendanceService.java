package me.jincrates.hr.service.attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.domain.attendance.AttendanceRepository;
import me.jincrates.hr.domain.employees.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<Attendance> checkIn(Attendance entity) {
        attendanceRepository.save(entity);

        return attendanceRepository.findByEmployeeId(entity.getEmployee().getId());
    }

    public List<Attendance> checkOut(Attendance entity) {
        Optional<Attendance> original = attendanceRepository.findByEmployeeIdAndWorkDate(entity.getEmployee().getId(), entity.getWorkDate());
        log.info("entity : " + entity.toString());

        original.ifPresent(attendance -> {
            attendance.update(entity.getInDate(), entity.getOutDate());

            attendanceRepository.save(attendance);
        });

        return attendanceRepository.findByEmployeeId(entity.getEmployee().getId());
    }
}
