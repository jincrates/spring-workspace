package me.jincrates.work.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CalendarDTO {

    private String title;
    private Date start;
    private Date end;
}
