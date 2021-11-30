package me.jincrates.work.controller;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.dto.AnnualDTO;
import me.jincrates.work.dto.AnnualUsedDTO;
import me.jincrates.work.dto.MemberDTO;
import me.jincrates.work.entity.Annual;
import me.jincrates.work.entity.AnnualUsed;
import me.jincrates.work.service.AnnualService;
import me.jincrates.work.service.AnnualUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/new")
    public ResponseEntity<AnnualUsed> usedAnnual(@RequestBody AnnualUsedDTO annualUsedDTO) {
        AnnualUsed result = usedService.usedAnnual(annualUsedDTO);

        log.info(annualUsedDTO.toString());
        log.info(result.toString());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/used")
    public String usedList() {
        return "annual/annualUsedList";
    }

    @GetMapping("/setting")
    public String setting(Model model) {
        model.addAttribute("createMember", new MemberDTO());
        return "annual/annualSetting";
    }
}
