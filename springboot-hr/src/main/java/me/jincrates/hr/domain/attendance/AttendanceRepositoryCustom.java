package me.jincrates.hr.domain.attendance;

import me.jincrates.hr.web.dto.attendance.AttendanceSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceRepositoryCustom {
    List<Attendance> getAttendanceList(AttendanceSearchDTO attendanceSearchDTO, Pageable pageable);
}
