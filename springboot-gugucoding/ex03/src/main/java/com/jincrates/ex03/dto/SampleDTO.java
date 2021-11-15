package com.jincrates.ex03.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

//@Data는 Getter/Setter, toString(), equls(), hashCode()를 자동으로 생성합니다.
@Data
@Builder(toBuilder = true)
public class SampleDTO {

    private Long sno;

    private String first;

    private String last;

    private LocalDateTime regTime;
}
