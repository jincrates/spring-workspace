package me.jincrates.work.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {

    @Id
    private String email;  //이메일을 아이디로 사용

    @Column(nullable = false)
    private String username;  //이름

    @Column(nullable = false)
    private String password;  //패스워드

    @Column(nullable = false)
    private String joinDate; //입사일

    @Column(nullable = false)
    private String status;  //상태(Y: 사용, N: 미사용)
}
