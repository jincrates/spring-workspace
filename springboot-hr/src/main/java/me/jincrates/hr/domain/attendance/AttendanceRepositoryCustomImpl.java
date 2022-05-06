package me.jincrates.hr.domain.attendance;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.java.Log;
import me.jincrates.hr.web.dto.attendance.AttendanceSearchDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log
public class AttendanceRepositoryCustomImpl implements AttendanceRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public AttendanceRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDate date = LocalDate.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            date = date.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            date = date.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            date = date.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            date = date.minusMonths(6);
        }

        return QAttendance.attendance.workDate.after(date);
    }

    @Override
    public List<Attendance> getAttendanceList(AttendanceSearchDTO searchDTO, Pageable pageable) {
        List<Attendance> content = queryFactory
                .selectFrom(QAttendance.attendance)
                .where(QAttendance.attendance.employee.eq(searchDTO.getEmployee()),
                        regDtsAfter(searchDTO.getSearchDateType()))
                .orderBy(QAttendance.attendance.workDate.asc())
                //.offset(pageable.getOffset())
                //.limit(pageable.getPageSize())
                .fetch();

        log.info(searchDTO.toString());
        log.info(pageable.toString());
        log.info(content.toString());
        return content;
    }
}
