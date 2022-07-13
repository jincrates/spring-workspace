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
public class AttendanceV2DTO {

    @Schema(description = "근무일자", type = "LocalDate", required = true)
    @NotNull(message = "근무일은 필수 입력 값입니다.")
    private String workDate;  // yyyy-MM-dd

    @Schema(description = "출근시간", type = "LocalDateTime")
    private String inDate; // hh:mm:ss

    @Schema(description = "퇴근시간", type = "LocalDateTime")
    private String outDate; // hh:mm:ss

    @Schema(description = "휴게시간", type = "int")
    private int breakTime;

    @Schema(description = "연장근무시간", type = "int")
    private int overTime;

    public AttendanceV2DTO(Attendance entity) {
        this.workDate = String.valueOf(entity.getWorkDate());
        this.inDate = String.valueOf(entity.getInDate());
        this.outDate = String.valueOf(entity.getOutDate());
        this.breakTime = entity.getBreakTime();
        this.overTime = entity.getOverTime();
    }
}
