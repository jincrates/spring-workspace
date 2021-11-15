package com.jincrates.cogito.security.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
    //password는 부로 클래스를 사용하므로 별도의 멤버 변수로 선언하지 않음
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    private Map<String, Object> attr;

    public AuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    public AuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeFromSocial(boolean fromSocial) {
        this.fromSocial = fromSocial;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
