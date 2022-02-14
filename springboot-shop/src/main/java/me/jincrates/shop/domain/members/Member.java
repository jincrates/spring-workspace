package me.jincrates.shop.domain.members;

import lombok.*;
import me.jincrates.shop.domain.BaseEntity;
import me.jincrates.shop.web.dto.members.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String name, String email, String password, String address, MemberRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public static Member createMember(MemberFormDto dto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(MemberRole.USER)
                .build();
        return member;
    }
}
