package me.jincrates.work.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;  //이메일을 아이디로 사용

    @Column(nullable = false)
    private String password;  //패스워드

    @Column(nullable = false)
    private String name;  //이름

    @Column(nullable = false)
    private String department;  //부서

    @Column(nullable = false)
    private String position;  //직위

    @Column(nullable = false)
    private Date joinDate; //입사일

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private int status;  //상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Annual> annual = new ArrayList<>();

    @Builder
    public Member(String name, String department, String position, String picture, int status, Role role) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.picture = picture;
        this.status = status;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
