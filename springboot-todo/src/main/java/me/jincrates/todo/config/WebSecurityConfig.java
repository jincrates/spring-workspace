package me.jincrates.todo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.config.security.JwtAuthenticationFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Log
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http
            // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
            .cors()
            .and()
            // csrf는 현재 사용하지 않으므로 disable
            .csrf().disable()
            // session 기반이 아님을 선언
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // /와 /auth/** 경로는 인증 안해도 됨
            .authorizeRequests().antMatchers("/", "/auth/**").permitAll()
            // 이외의 모든 경로는 인증 해야됨
            .anyRequest().authenticated()
        ;

        // filter 등록
        // 매 요청마다 CorsFilter 실행한 후에 jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
    }
}
