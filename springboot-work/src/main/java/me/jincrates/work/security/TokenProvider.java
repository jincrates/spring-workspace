package me.jincrates.work.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.entity.Member;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "ZXlKaGJHY2lPaUpJVXpVeE1pSjkuZXlKemRXSWlPaUl4SWl3aWFYTnpJam9pZDI5eWF5QmhjSEFpTENKcFlYUWlPakUyTXpnM09UZ3hPRElzSW1WNGNDSTZNVFl6T0RnNE5EVTRNbjAuMW9PVFdnNVZjRl9URmdKSDhYbHZwbDRqeDJhTDBGbmlXZnJhY0FVT0dxNlF6VGVJUmcyakhKemNxWVVvOGhQbktwUzZvWVlHLUdPY1ctQ0ZvLVlTY2c=";

    public String create(Member member) {
        //기한은 지금부터 1일로 설정
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        //JWT Token 생성
        return Jwts.builder()
                //header에 들어갈 내용 및 서명을 위하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                //payload에 들어갈 내용
                .setSubject(member.getId().toString()) //sub
                .setIssuer("work app") //iss
                .setIssuedAt(new Date())  //iat
                .setExpiration(expiryDate) //exp
                .compact();
    }

    public String validateAndGetMemberId(String token) {
        // parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
        // 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
        // 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        // 그중 우리는 memberId가 필요하므로 getBody를 부른다.
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
