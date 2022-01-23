package me.jincrates.shop.domain.members;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jincrates.shop.web.dto.members.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

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

    public static Member createMember(MemberFormDto dto, PasswordEncoder passwordEncoder) {
        //Member member = new Member();
    }
}
