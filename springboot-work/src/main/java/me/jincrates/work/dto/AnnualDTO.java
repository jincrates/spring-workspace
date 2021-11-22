package me.jincrates.work.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;
import me.jincrates.work.entity.Member;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnualDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long baseYear;

    @NotNull
    private Member member;

    @NotNull
    private String joinDate;

    @NotNull
    private double annual;  //발생연차

    @NotNull
    private double month; //발생월차

    @NotNull
    private double adjusted; //조정연차

    @NotNull
    private double used; //사용연차
}
