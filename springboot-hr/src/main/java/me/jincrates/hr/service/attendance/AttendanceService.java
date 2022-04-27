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

    public List<Attendance> retrieve(Attendance entity) {
        return attendanceRepository.findByEmployeeId(entity.getEmployee().getId());
    }

    public List<Attendance> create(Attendance entity) {
        if (entity == null || entity.getWorkDate() == null) {
            throw new RuntimeException("Invalid arguments.");
        }

        if (attendanceRepository.existsByEmployeeIdAndWorkDate(entity.getEmployee().getId(), entity.getWorkDate())) {
            log.warning("이미 데이터가 존재합니다. " + entity.getId());
            throw new RuntimeException("이미 데이터가 존재합니다.");
        }

        attendanceRepository.save(entity);

        return retrieve(entity);
    }

    public List<Attendance> update(Attendance entity) {
        Optional<Attendance> original = attendanceRepository.findByEmployeeIdAndWorkDate(entity.getEmployee().getId(), entity.getWorkDate());

        if (original.isEmpty()) {
            log.warning("수정할 데이터가 존재하지 않습니다. " + entity.getId());
            throw new RuntimeException("수정할 데이터가 존재하지 않습니다.");
        }
        original.ifPresent(attendance -> {
            attendance.update(entity.getInDate(), entity.getOutDate());
            attendanceRepository.save(attendance);
        });

        return retrieve(entity);
    }

    public List<Attendance> delete(Attendance entity) {
        try {
            Optional<Attendance> original = attendanceRepository.findByEmployeeIdAndWorkDate(entity.getEmployee().getId(), entity.getWorkDate());

            if (original.isEmpty()) {
                log.warning("삭제할 데이터가 존재하지 않습니다. " + entity.getId());
                throw new RuntimeException("삭제할 데이터가 존재하지 않습니다.");
            }

            attendanceRepository.deleteById(original.get().getId());

        } catch (Exception e) {
            log.warning("데이터를 삭제하지 못하였습니다. " + entity.getId());
            log.warning("error : " + e.getMessage());
            throw new RuntimeException("데이터를 삭제하지 못하였습니다.");
        }

        return retrieve(entity);
    }
}
