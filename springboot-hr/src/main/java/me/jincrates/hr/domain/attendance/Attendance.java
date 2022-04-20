package me.jincrates.hr.domain.attendance;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;
import me.jincrates.hr.web.dto.attendance.AttendanceDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
    private Long id;

    //사원키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //근무일자
    @Column(nullable = false)
    private String workDate;

    //출근일자
    private LocalDateTime inDate;

    //퇴근일자
    private LocalDateTime outDate;

    //휴게시간
    private int breakTime;

    //연장근무시간
    private int overTime;

    //지각여부
    private boolean isLate;

    //재택여부
    private boolean isHome;

    //근태상태
    @Enumerated(EnumType.STRING)
    private AttendanceStatus statue;

    @Builder
    public Attendance(Employee employee, String workDate, LocalDateTime inDate, LocalDateTime outDate, int breakTime, int overTime, boolean isLate, boolean isHome) {
        this.employee = employee;
        this.workDate = workDate;
        this.inDate = inDate;
        this.outDate = outDate;
        this.breakTime = breakTime;
        this.overTime = overTime;
        this.isLate = isLate;
        this.isHome = isHome;
    }

    public static Attendance createAttendance(AttendanceDTO dto) {
        Attendance attendance = Attendance.builder()
                .workDate(dto.getWorkDate())
                .inDate(dto.getInDate())
                .outDate(dto.getOutDate())
                .breakTime(dto.getBreakTime())
                .overTime(dto.getOverTime())
                .isLate(dto.isLate())
                .isHome(dto.isHome())
                .build();
        return attendance;
    }
}
