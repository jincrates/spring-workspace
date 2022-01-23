package me.jincrates.shop.web.dto.members;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.shop.domain.members.Member;

@Getter
@NoArgsConstructor
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String address;

    @Builder
    public MemberFormDto(Member entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.address = entity.getAddress();
    }
}
