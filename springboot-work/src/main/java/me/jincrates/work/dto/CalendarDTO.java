package me.jincrates.work.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CalendarDTO {

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date start;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date end;
}