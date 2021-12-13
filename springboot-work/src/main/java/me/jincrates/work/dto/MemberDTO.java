package me.jincrates.work.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;
import me.jincrates.work.entity.Member;

@Builder
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    private String token;
    private String email;
    private String username;
    private String password;
    private String joinDate;
}
