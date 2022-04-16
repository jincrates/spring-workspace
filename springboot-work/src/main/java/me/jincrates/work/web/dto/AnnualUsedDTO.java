package me.jincrates.work.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;
import me.jincrates.work.entity.Member;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnualUsedDTO {

    @NotNull
    private Long baseYear;

    @NotNull
    private String member;

    @NotNull
    private double used;   //사용일수

    @NotNull
    private Date usedFromDate;  //사용 시작일

    @NotNull
    private Date usedToDate;  //사용 종료일

    @NotNull
    private String reason;  //사유
}
