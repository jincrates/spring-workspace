package me.jincrates.hr.domain.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>,
        QuerydslPredicateExecutor<Attendance>, AttendanceRepositoryCustom {

    List<Attendance> findByEmployeeId(Long employeeId);

    Optional<Attendance> findByEmployeeIdAndWorkDate(Long employeeId, LocalDate workDate);

    boolean existsByEmployeeIdAndWorkDate(Long employeeId, LocalDate workDate);
}
