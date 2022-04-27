package me.jincrates.hr.web.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.attendance.Attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceDTO {

    private LocalDate workDate;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    //private long workTime;

    private int breakTime;

    private int overTime;

    public AttendanceDTO(Attendance entity) {
        this.workDate = entity.getWorkDate();
        this.inDate = entity.getInDate();
        this.outDate = entity.getOutDate();
        //this.workTime = entity.getInDate().until(entity.getOutDate(), ChronoUnit.MINUTES);
        this.breakTime = entity.getBreakTime();
        this.overTime = entity.getOverTime();
    }
}
