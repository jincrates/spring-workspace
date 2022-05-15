package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AttendanceController {

    @GetMapping(value = "/attendance")
    public String attendanceList() {
        return "attendance/attendanceList";
    }

    @GetMapping(value = "/attendance/save")
    public String attendanceForm() {
        return "attendance/attendanceForm";
    }
}
