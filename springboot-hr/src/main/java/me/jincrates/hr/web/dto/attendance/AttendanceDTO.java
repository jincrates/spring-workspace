package me.jincrates.hr.web.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.attendance.Attendance;
import me.jincrates.hr.domain.attendance.AttendanceStatus;
import me.jincrates.hr.domain.employees.Employee;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDTO {

    private String email;

    private String workDate;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    private int breakTime;

    private int overTime;

    private boolean isLate;

    private boolean isHome;

    private AttendanceStatus status;

    public AttendanceDTO(Attendance entity) {
        this.email = entity.getEmployee().getEmail();
        this.workDate = entity.getWorkDate();
        this.inDate = entity.getInDate();
        this.outDate = entity.getOutDate();
        this.breakTime = entity.getBreakTime();
        this.overTime = entity.getOverTime();
        this.isLate = entity.isLate();
        this.isHome = entity.isHome();
    }
}
