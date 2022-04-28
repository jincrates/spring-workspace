package me.jincrates.hr.web.dto.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.attendance.Attendance;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "출퇴근 데이터 전송 객체")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

    @Schema(description = "근무일자", type = "LocalDate", required = true)
    @NotNull(message = "근무일은 필수 입력 값입니다.")
    private LocalDate workDate;

    @Schema(description = "출근시간", type = "LocalDateTime")
    private LocalDateTime inDate;

    @Schema(description = "퇴근시간", type = "LocalDateTime")
    private LocalDateTime outDate;

    //private long workTime;

    @Schema(description = "휴게시간", type = "int")
    private int breakTime;

    @Schema(description = "연장근무시간", type = "int")
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
