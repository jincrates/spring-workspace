package me.jincrates.hr.web.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.attendance.Attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDTO {

    private LocalDate workDate;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    private int breakTime;

    private int overTime;

    public AttendanceDTO(Attendance entity) {
        this.workDate = entity.getWorkDate();
        this.inDate = entity.getInDate();
        this.outDate = entity.getOutDate();
        this.breakTime = entity.getBreakTime();
        this.overTime = entity.getOverTime();
    }
}
