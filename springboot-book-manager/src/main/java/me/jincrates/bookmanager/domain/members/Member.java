package me.jincrates.bookmanager.domain.members;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.bookmanager.common.Status;

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
}
