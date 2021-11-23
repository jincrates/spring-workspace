package me.jincrates.work.controller;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.entity.Annual;
import me.jincrates.work.service.AnnualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/annual")
public class AnnualController {

    @Autowired
    private AnnualService service;

    @GetMapping("")
    public String list(Model model) {
        List<Annual> annualList = service.findAll();

        log.info(annualList.toString());

        model.addAttribute("annualList", annualList);
        return "annual/annualList";
    }
}
