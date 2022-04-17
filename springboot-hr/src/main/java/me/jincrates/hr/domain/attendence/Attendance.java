package me.jincrates.hr.domain.attendence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.hr.domain.employees.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Employee employee;

    private String workDate;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    private int breakTime;

    private int overTime;

    private boolean isLate;

    private boolean isHome;

    private AttendanceStatus statue;

}
