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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    public ResponseEntity<List<CalendarDTO>> usedListJson(@AuthenticationPrincipal String memberId) {
        List<AnnualUsed> usedList = usedService.findAll();
        List<CalendarDTO> calendarList = new ArrayList<>();

        //usedList에서 필요한 데이터만 calendarList에 담기
        for (AnnualUsed annualUsed : usedList) {
            Date endAdd = new Date(annualUsed.getUsedToDate().getTime() + (1000 * 60 * 60 * 24));

            CalendarDTO calendarDTO = CalendarDTO.builder()
                    .usedId(annualUsed.getId())
                    .title(annualUsed.getReason())
                    .start(annualUsed.getUsedFromDate())
                    .end(endAdd)
                    .build();
            calendarList.add(calendarDTO);
        }

        return new ResponseEntity<>(calendarList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<AnnualUsed> usedAnnual(@RequestBody AnnualUsedDTO request) {
        AnnualUsed result = usedService.usedAnnual(request);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/used")
    public String usedList(Model model) {
        List<AnnualUsed> annualUsedList = usedService.findAll();
        model.addAttribute("annualUsedList", annualUsedList);

        return "annual/annualUsedList";
    }

    @DeleteMapping(value = "/used/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> removeUsed(@PathVariable("id") Long id) {
        usedService.remove(id);

        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @PutMapping(value = "/used/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modifyUsed(@PathVariable("id") Long id, @RequestBody AnnualUsedDTO request) {
        usedService.update(id, request);

        return new ResponseEntity<>("modify", HttpStatus.OK);
    }


    @GetMapping("/setting")
    public String setting(Model model) {
        model.addAttribute("createMember", new MemberDTO());
        return "annual/annualSetting";
    }
}
