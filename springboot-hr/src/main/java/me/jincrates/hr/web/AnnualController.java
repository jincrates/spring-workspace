package me.jincrates.hr.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AnnualController {

    @GetMapping(value = "/annual")
    public String annualList() {
        return "annual/annualList";
    }

    @GetMapping(value = "/annual/setting")
    public String annualSetting() {
        return "annual/annualSetting";
    }

    @GetMapping(value = "/annual/save")
    public String annualForm() {
        return "annual/annualForm";
    }

    @GetMapping(value = "/annual/used/id")
    public String annualUsed() {
        return "annual/annualUsedList";
    }

    @GetMapping(value = "/annual/adjusted/id")
    public String annualAdjustedList() {
        return "annual/annualForm";
    }

}