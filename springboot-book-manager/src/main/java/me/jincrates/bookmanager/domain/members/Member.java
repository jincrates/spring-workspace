package me.jincrates.bookmanager.domain.members;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.bookmanager.common.Status;
import me.jincrates.bookmanager.web.dto.MemberDto;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter @ToString
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Member(Long id, String name, String email, String password, String phoneNumber, MemberRole role, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

    public static Member createMember(MemberDto memberDto) {
        return Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .role(memberDto.getRole())
                .status(memberDto.getStatus())
                .build();
    }

    public static MemberDto of(Member entity) {
        return MemberDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }
}
