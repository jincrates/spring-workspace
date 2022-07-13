package me.jincrates.hr.domain.attendance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.BaseEntity;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.web.dto.attendance.AttendanceDTO;
import me.jincrates.hr.web.dto.attendance.AttendanceV2DTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Schema(description = "출퇴근")
@Getter
@NoArgsConstructor
@Entity
@Table(name="attendance")
public class Attendance extends BaseEntity {
    @Schema(description = "출퇴근 PK")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
    private Long id;

    @Schema(description = "사원 FK")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Schema(description = "근무일자", example = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate workDate;

    @Schema(description = "출근일시")
    private LocalDateTime inDate;

    @Schema(description = "퇴근일시")
    private LocalDateTime outDate;

    @Schema(description = "휴게시간")
    private int breakTime;

    @Schema(description = "연장근무시간")
    private int overTime;

    @Builder
    public Attendance(Employee employee, LocalDate workDate, LocalDateTime inDate, LocalDateTime outDate, int breakTime, int overTime) {
        this.employee = employee;
        this.workDate = workDate;
        this.inDate = inDate;
        this.outDate = outDate;
        this.breakTime = breakTime;
        this.overTime = overTime;
    }

    public void update(LocalDateTime inDate, LocalDateTime outDate, int breakTime, int overTime) {
        this.inDate = inDate;
        this.outDate = outDate;
        this.breakTime = breakTime;
        this.overTime = overTime;
    }

    public long getWorkDiff(LocalDateTime inDate, LocalDateTime outDate) {
        if (inDate == null || outDate == null) {
            return 0L;
        }

        return inDate.until(outDate, ChronoUnit.MINUTES);
    }

    public static Attendance toEntity(Employee employee, AttendanceDTO dto) {
        return Attendance.builder()
                .employee(employee)
                .workDate(dto.getWorkDate())
                .inDate(dto.getInDate())
                .outDate(dto.getOutDate())
                .breakTime(dto.getBreakTime())
                .overTime(dto.getOverTime())
                .build();
    }

    public static Attendance toEntity(Employee employee, AttendanceV2DTO dto) {
        return Attendance.builder()
                .employee(employee)
                .workDate(LocalDate.parse(dto.getWorkDate()))
                .inDate(LocalDateTime.parse(dto.getWorkDate() + " " + dto.getInDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .outDate(LocalDateTime.parse(dto.getWorkDate() + " " + dto.getOutDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .breakTime(dto.getBreakTime())
                .overTime(dto.getOverTime())
                .build();
    }
}
