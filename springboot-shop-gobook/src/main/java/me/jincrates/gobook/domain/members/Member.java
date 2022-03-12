package me.jincrates.gobook.domain.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.gobook.domain.BaseEntity;
import me.jincrates.gobook.web.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter @ToString
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

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  //암호화처리
                .role(MemberRole.ADMIN)
                .build();
        return member;
    }
}
