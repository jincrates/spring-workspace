package com.jincrates.cogito.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "사용자 데이터 전송 객체")
public class MemberDTO {

    private String email;

    private String password;

    private String name;

    private boolean fromSocial;
}
