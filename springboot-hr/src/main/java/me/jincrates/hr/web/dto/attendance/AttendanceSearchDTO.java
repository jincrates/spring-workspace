package me.jincrates.hr.web.dto.attendance;

import lombok.Data;
import me.jincrates.hr.domain.employees.Employee;

@Data
public class AttendanceSearchDTO {

    private Employee employee;

    private String searchDateType = "all";

    private String searchQuery = "";
}
