package me.jincrates.work.controller;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.dto.AnnualDTO;
import me.jincrates.work.dto.AnnualUsedDTO;
import me.jincrates.work.dto.CalendarDTO;
import me.jincrates.work.dto.MemberDTO;
import me.jincrates.work.entity.Annual;
import me.jincrates.work.entity.AnnualUsed;
import me.jincrates.work.service.AnnualService;
import me.jincrates.work.service.AnnualUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/annual")
public class AnnualController {

    @Autowired
    private AnnualService service;

    @Autowired
    private AnnualUsedService usedService;

    @GetMapping("")
    public String list(Model model) {
        List<Annual> annualList = service.findAll();

        log.info(annualList.toString());

        model.addAttribute("annualList", annualList);
        return "annual/annualList";
    }

    @GetMapping("/new")
    public String view(Model model) {
        List<AnnualUsed> annualUsedList = usedService.findAll();
        model.addAttribute("createAnnual", annualUsedList);

        return "annual/annualCreateForm";
    }

//    @GetMapping("/new/json")
//    public ResponseEntity<List<AnnualUsed>> usedListJson() {
//        return new ResponseEntity<>(usedService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/new/json")
    public ResponseEntity<List<CalendarDTO>> usedListJson() {
        List<AnnualUsed> usedList = usedService.findAll();
        List<CalendarDTO> calendarList = new ArrayList<>();

        //usedList에서 필요한 데이터만 calendarList에 담기
        for (AnnualUsed annualUsed : usedList) {
            CalendarDTO calendarDTO = CalendarDTO.builder()
                    .title(annualUsed.getReason())
                    .start(annualUsed.getUsedFromDate())
                    .end(annualUsed.getUsedToDate())
                    .build();
            calendarList.add(calendarDTO);
        }

        return new ResponseEntity<>(calendarList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<AnnualUsed> usedAnnual(@RequestBody AnnualUsedDTO annualUsedDTO) {
        AnnualUsed result = usedService.usedAnnual(annualUsedDTO);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/used")
    public String usedList(Model model) {
        List<AnnualUsed> annualUsedList = usedService.findAll();
        model.addAttribute("annualUsedList", annualUsedList);

        return "annual/annualUsedList";
    }

    @GetMapping("/setting")
    public String setting(Model model) {
        model.addAttribute("createMember", new MemberDTO());
        return "annual/annualSetting";
    }
}
