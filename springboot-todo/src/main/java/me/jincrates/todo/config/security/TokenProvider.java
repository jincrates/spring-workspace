package me.jincrates.todo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import me.jincrates.todo.domain.users.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Log
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "PpS7cM7Zpjv2gdVWSiPoRNv5s5LTveR3L7pkHsfNaFkYIaJQbx5taJf0GJJzUZD2dmpXCn9HHBDSD2eJDwkmYA";

    public String create(User user) {
        //기한은 지금부터 1일로 설정
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        //JWT Token 생성
        return Jwts.builder()
                //header에 들어갈 내용 및 서명을 위한 SECRET_KET
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                //payload에 들어갈 내용
                .setSubject(user.getId())   //sub
                .setIssuer("todo app")      //iss
                .setIssuedAt(new Date())    //iat
                .setExpiration(expiryDate)  //exp
                .compact();
    }

    public String validateAndGetUserId(String token) {
        //parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
        //헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
        //위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        //그중 우리는 userId가 필요하므로 getBody를 부른다.
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}